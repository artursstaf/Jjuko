package com.stafanovics.arturs.jjuko.dataClasses

import java.io.Serializable

data class City(val name: String = "") : Serializable, Comparable<City> {
    override fun compareTo(other: City): Int {
        return when {
            this < other -> -1
            this > other -> 1
            this == other -> 0
            else -> 0
        }
    }
}