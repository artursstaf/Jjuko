package com.stafanovics.arturs.jjuko.DataClasses

import com.google.firebase.firestore.Exclude
import java.io.Serializable

data class Deal(val craftsman: Craftsman = Craftsman(), val date: Long = 0, val time: String = "",
                val accepted: Boolean = false, val description: String = "", @get:Exclude var id: String = "") : Serializable