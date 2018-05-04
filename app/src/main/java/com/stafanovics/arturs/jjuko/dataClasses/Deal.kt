package com.stafanovics.arturs.jjuko.dataClasses

import com.google.firebase.firestore.Exclude
import java.io.Serializable

data class Deal(val userId: String = "", val craftsman: Craftsman = Craftsman(), val date: Long = 0,
                val accepted: Boolean = false, val description: String = "", @get:Exclude var id: String = "") : Serializable