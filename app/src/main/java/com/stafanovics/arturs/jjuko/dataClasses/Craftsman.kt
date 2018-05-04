package com.stafanovics.arturs.jjuko.dataClasses

import com.google.firebase.firestore.Exclude
import java.io.Serializable


data class Craftsman(val averageRating: Float = 0.0f, val name: String = "", val surname: String = "",
                     val phone: String = "", val description: String = "", val locations: List<City> = emptyList(),
                     val speciality: List<Speciality> = emptyList(), @get:Exclude var id: String = "") : Serializable