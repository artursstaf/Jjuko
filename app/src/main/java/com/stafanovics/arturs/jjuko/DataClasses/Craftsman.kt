package com.stafanovics.arturs.jjuko.DataClasses

import com.google.firebase.firestore.Exclude
import java.io.Serializable


data class Craftsman(val averageRating: Float = 0.0f, val name: String = "", val surname: String = "",
                     val phone: String = "", val description: String = "", val locations: List<City> = emptyList<City>(),
                     val speciality: List<Speciality> = emptyList<Speciality>(), @get:Exclude var id: String = "") : Serializable