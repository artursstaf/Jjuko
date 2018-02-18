package com.stafanovics.arturs.jjuko.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.stafanovics.arturs.jjuko.Adapters.LocationlistAdapter
import com.stafanovics.arturs.jjuko.DataClasses.City
import com.stafanovics.arturs.jjuko.DataClasses.Craftsman
import com.stafanovics.arturs.jjuko.Events.CraftsmanUpdated.OnCraftsmanUpdatedEventListener
import com.stafanovics.arturs.jjuko.MyApplication
import com.stafanovics.arturs.jjuko.R
import kotlinx.android.synthetic.main.activity_location_list.*
import org.jetbrains.anko.toast
import java.util.*
import kotlin.collections.LinkedHashSet


class LocationListActivity : AppCompatActivity() {

    //Static objects
    companion object {
        private const val LOG_FIREBASE = "Firestore"
        private const val RC_SIGN_IN = 123
        private val providers = listOf(AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build())
    }

    //members
    private var mUsername: String = ""
    private val mLocationAdapter by lazy { LocationlistAdapter(this, R.layout.location_list_item, ArrayList<City>()) }
    private val mFirebaseAuth = FirebaseAuth.getInstance()
    private val mMyApplication by lazy { application as MyApplication }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_list)
        title = getString(R.string.title_location_list)
        locationListView.adapter = mLocationAdapter
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
            R.id.menu_profile -> {
                toast("Nestrādā :(")
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
                mUsername = user.displayName ?: ""
            } else {
                //Signout cleanup
                mUsername = ""
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

