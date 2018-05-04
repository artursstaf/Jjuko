package com.stafanovics.arturs.jjuko

import android.app.Application
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.QuerySnapshot
import com.stafanovics.arturs.jjuko.constants.LOGD_FIRESTORE
import com.stafanovics.arturs.jjuko.dataClasses.Craftsman
import com.stafanovics.arturs.jjuko.dataClasses.Deal
import com.stafanovics.arturs.jjuko.events.OnCraftsmanUpdatedEventListener


class MyApplication(val craftsmen: MutableList<Craftsman> = ArrayList(), val deals: MutableList<Deal> = ArrayList(),
                    private val onCraftsmanUpdatedEventListeners: MutableList<OnCraftsmanUpdatedEventListener> = ArrayList()
) : Application() {

    private val mFirestore by lazy { FirebaseFirestore.getInstance() }
    private var mLocationListenerRegistration: ListenerRegistration? = null

    override fun onCreate() {
        super.onCreate()
        //Bad - because listening to all changes in database
        mLocationListenerRegistration = mFirestore.collection("Craftsmen").addSnapshotListener(mLocationEventListener)
    }


    //Managing listeners for Activities
    fun addOnCraftsmanUpdateListener(listener: OnCraftsmanUpdatedEventListener) {
        onCraftsmanUpdatedEventListeners.add(listener)
    }

    fun removeOnCraftsmanUpdateListener(listener: OnCraftsmanUpdatedEventListener) {
        onCraftsmanUpdatedEventListeners.removeAll { it == listener }
    }

    //FireStoreListener
    private val mLocationEventListener = listener@ { querySnapshot: QuerySnapshot?, _: FirebaseFirestoreException? ->
        if (querySnapshot == null) return@listener
        craftsmen.clear()
        querySnapshot.forEach {
            val id = it.id
            try {
                craftsmen.add(it.toObject(Craftsman::class.java).also { it.id = id })
            } catch (e: Exception) {
                Log.d(LOGD_FIRESTORE, "Error deserializing firestore snapshot, item skipped")
            }
        }
        onCraftsmanUpdatedEventListeners.forEach { it.onEvent(craftsmen) }
        Unit
    }
}