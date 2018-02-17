package com.stafanovics.arturs.jjuko

import android.app.Application
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.QuerySnapshot
import com.stafanovics.arturs.jjuko.DataClasses.Craftsman
import com.stafanovics.arturs.jjuko.DataClasses.Deal
import com.stafanovics.arturs.jjuko.Events.CraftsmanUpdated.OnCraftsmanUpdatedEventListener


class MyApplication(val craftsmen: MutableList<Craftsman> = ArrayList<Craftsman>(), val deals: MutableList<Deal> = ArrayList<Deal>(),
                    private val onCraftsmanUpdatedEventListeners: MutableList<OnCraftsmanUpdatedEventListener> = ArrayList<OnCraftsmanUpdatedEventListener>()
) : Application() {

    private val mFirestore by lazy { FirebaseFirestore.getInstance() }
    private var mLocationListenerRegistration: ListenerRegistration? = null

    override fun onCreate() {
        super.onCreate()
        mLocationListenerRegistration = mFirestore.collection("Craftsmen").addSnapshotListener(mLocationEventListener)
    }


    //Listeners for Activities
    fun addOnCraftsmanUpdateListener(listener: OnCraftsmanUpdatedEventListener) {
        onCraftsmanUpdatedEventListeners.add(listener)
    }

    fun removeOnCraftsmanUpdateListener(listener: OnCraftsmanUpdatedEventListener) {
        onCraftsmanUpdatedEventListeners.removeAll { it == listener }
    }

    //FireStoreListener
    private val mLocationEventListener = { querySnapshot: QuerySnapshot?, _: FirebaseFirestoreException? ->
        craftsmen.clear()
        querySnapshot?.forEach {
            val id = it.id
            try {
                craftsmen.add(it.toObject(Craftsman::class.java).also { it.id = id })
            } catch (e: Exception) {
                Log.d("FIRESTORE", "Error serializing firestore snapshot, item skipped")
            }

        }
        onCraftsmanUpdatedEventListeners.forEach { it.onEvent(craftsmen) }
        Unit
    }
}