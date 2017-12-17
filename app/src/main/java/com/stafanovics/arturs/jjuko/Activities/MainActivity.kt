package com.stafanovics.arturs.jjuko.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.stafanovics.arturs.jjuko.Adapters.LocationAdapter
import com.stafanovics.arturs.jjuko.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {

    companion object {
        private val RC_SIGN_IN = 123
        val providers = listOf(AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build())
    }

    private var mUsername: String? = null
    private val mFirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val mFirebaseAuthStateListener: FirebaseAuth.AuthStateListener by lazy {
        FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                mUsername = user.displayName
            } else {
                mUsername = null
                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(providers)
                                .setIsSmartLockEnabled(false)
                                .build(),
                        RC_SIGN_IN);
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Lokācija"
        val locationAdapter = LocationAdapter(this, R.layout.city_list_view, listOf("Liepāja", "Salaspilps", "Ogre"
                , "Olaine", "Jūrmala", "Ventspils", "Jelgava", "Daugavpils", "Jēkabpils", "Alūksne", "Rēzekne", "Valmiera", "Cēsis", "Sigulda", "Kuldīga", "Tukums", "Talsi").sorted())
        locationListView.adapter = locationAdapter


    }

    override fun onResume() {
        super.onResume()
        //mFirebaseAuth.addAuthStateListener(mFirebaseAuthStateListener)
    }

    override fun onPause() {
        super.onPause()
        //mFirebaseAuth.removeAuthStateListener(mFirebaseAuthStateListener)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            R.id.menu_logout -> {
                AuthUI.getInstance().signOut(this); return true
            }
            R.id.menu_profile ->{
                toast("Nestrādā :(")
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}
