package com.stafanovics.arturs.jjuko.events

import com.stafanovics.arturs.jjuko.dataClasses.Craftsman

interface OnCraftsmanUpdatedEventListener {
    fun onEvent(craftsmen: List<Craftsman>)
}