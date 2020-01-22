package com.stafanovics.arturs.jjuko.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.stafanovics.arturs.jjuko.MyApplication
import com.stafanovics.arturs.jjuko.R
import com.stafanovics.arturs.jjuko.adapters.LocationlistAdapter
import com.stafanovics.arturs.jjuko.constants.LOGD_FIRESTORE
import com.stafanovics.arturs.jjuko.dataClasses.City
import com.stafanovics.arturs.jjuko.dataClasses.Craftsman
import com.stafanovics.arturs.jjuko.dataClasses.Deal
import com.stafanovics.arturs.jjuko.events.OnCraftsmanUpdatedEventListener
import kotlinx.android.synthetic.main.activity_location_list.*
import org.jetbrains.anko.toast
import java.util.*
import kotlin.collections.LinkedHashSet


class LocationListActivity : AppCompatActivity() {

    //Static objects
    companion object {
        private const val RC_SIGN_IN = 123
        private val providers = listOf(AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build())
    }

    //members
    private var mUsername: String = ""
    private val mLocationAdapter by lazy { LocationlistAdapter(this, R.layout.location_list_item, ArrayList()) }
    private val mFirebaseAuth = FirebaseAuth.getInstance()
    private val mMyApplication by lazy { application as MyApplication }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_list)
        title = getString(R.string.title_location_list)
        list_location_list.adapter = mLocationAdapter
    }


    override fun onStart() {
        mLocationAdapter.addAll(getCitiesFromCraftsmen(mMyApplication.craftsmen))
        mMyApplication.addOnCraftsmanUpdateListener(mCraftsmanListener)
        mFirebaseAuth.addAuthStateListener(mFirebaseAuthStateListener)
        super.onStart()
    }

    override fun onStop() {
        mLocationAdapter.clear()
        mMyApplication.removeOnCraftsmanUpdateListener(mCraftsmanListener)
        mFirebaseAuth.removeAuthStateListener(mFirebaseAuthStateListener)
        super.onStop()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_logout -> {
                AuthUI.getInstance().signOut(this)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun getCitiesFromCraftsmen(list: List<Craftsman>): Collection<City> {
        val listOfCities: List<List<City>> = list.map { it.locations }
        val cities = LinkedHashSet<City>()
        listOfCities.forEach { it.forEach { cities.add(it) } }
        return cities
    }

    private val mCraftsmanListener = object : OnCraftsmanUpdatedEventListener {
        override fun onEvent(craftsmen: List<Craftsman>) {
            mLocationAdapter.clear()
            mLocationAdapter.addAll(getCitiesFromCraftsmen(craftsmen))
        }
    }

    //Auth listener
    private val mFirebaseAuthStateListener: FirebaseAuth.AuthStateListener by lazy {
        FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser

            if (user != null) {
                //Signin
                mMyApplication.userId = user.uid
                mUsername = user.displayName ?: ""
                val x = listener@{ querySnapshot: QuerySnapshot?, _: FirebaseFirestoreException? ->
                    if (querySnapshot == null) return@listener
                    mMyApplication.deals.clear()
                    querySnapshot.forEach {
                        val id = it.id
                        try {
                            val obj = it.toObject(Deal::class.java).also { it.id = id }
                            if (obj.userId == user.uid) {
                                mMyApplication.deals.add(obj)
                            }

                        } catch (e: Exception) {
                            Log.d(LOGD_FIRESTORE, "Error deserializing firestore snapshot, item skipped")
                        }
                    }
                    mMyApplication.onDealUpdatedEventListeners.forEach { it.onEvent(mMyApplication.deals) }
                    Unit
                }
                mMyApplication.mDealListenerRegistration?.remove()
                mMyApplication.mDealListenerRegistration = mMyApplication.mFirestore.collection("Reservations").addSnapshotListener(x)


            } else {
                //Signout cleanup
                mUsername = ""
                mMyApplication.userId = ""
                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(providers)
                                .setIsSmartLockEnabled(false)
                                .build(),
                        RC_SIGN_IN)
            }
        }
    }
}

