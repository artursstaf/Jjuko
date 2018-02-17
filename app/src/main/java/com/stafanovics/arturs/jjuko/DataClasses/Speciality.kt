package com.stafanovics.arturs.jjuko.DataClasses

import java.io.Serializable

data class Speciality(val name: String = "") : Serializable, Comparable<Speciality> {
    override fun compareTo(other: Speciality): Int {
        return when {
            this < other -> -1
            this > other -> 1
            this == other -> 0
            else -> 0
        }
    }
}