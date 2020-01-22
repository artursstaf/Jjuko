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
import com.stafanovics.arturs.jjuko.events.OnDealUpdatedEventListener


class MyApplication(var userId: String = "", val craftsmen: MutableList<Craftsman> = ArrayList(), val deals: MutableList<Deal> = ArrayList(),
                    private val onCraftsmanUpdatedEventListeners: MutableList<OnCraftsmanUpdatedEventListener> = ArrayList(), val onDealUpdatedEventListeners: MutableList<OnDealUpdatedEventListener> = ArrayList()
) : Application() {

    private var mLocationListenerRegistration: ListenerRegistration? = null
    var mDealListenerRegistration: ListenerRegistration? = null

    val mFirestore by lazy { FirebaseFirestore.getInstance() }

    override fun onCreate() {
        super.onCreate()
        mLocationListenerRegistration = mFirestore.collection("Craftsmen").addSnapshotListener(mLocationEventListener)
        mDealListenerRegistration = mFirestore.collection("Reservations").addSnapshotListener(mDealEventListener)
    }

    override fun onTerminate() {
        super.onTerminate()
        mLocationListenerRegistration?.remove()
        mDealListenerRegistration?.remove()
    }

    //FireStoreListener
    private val mLocationEventListener = listener@{ querySnapshot: QuerySnapshot?, _: FirebaseFirestoreException? ->
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

    //FireStoreListener
    val mDealEventListener = listener@{ querySnapshot: QuerySnapshot?, _: FirebaseFirestoreException? ->
        if (querySnapshot == null) return@listener
        deals.clear()
        querySnapshot.forEach {
            val id = it.id
            try {
                val obj = it.toObject(Deal::class.java).also { it.id = id }
                if(obj.userId == userId){
                    deals.add(obj)
                }

            } catch (e: Exception) {
                Log.d(LOGD_FIRESTORE, "Error deserializing firestore snapshot, item skipped")
            }
        }
        onDealUpdatedEventListeners.forEach { it.onEvent(deals) }
        Unit
    }

    //Managing listeners for Activities
    fun addOnDealpdateListener(listener: OnDealUpdatedEventListener) {
        onDealUpdatedEventListeners.add(listener)
    }

    fun removeOnDealUpdateListener(listener: OnDealUpdatedEventListener) {
        onDealUpdatedEventListeners.removeAll { it == listener }
    }

    //Managing listeners for Activities
    fun addOnCraftsmanUpdateListener(listener: OnCraftsmanUpdatedEventListener) {
        onCraftsmanUpdatedEventListeners.add(listener)
    }

    fun removeOnCraftsmanUpdateListener(listener: OnCraftsmanUpdatedEventListener) {
        onCraftsmanUpdatedEventListeners.removeAll { it == listener }
    }


}