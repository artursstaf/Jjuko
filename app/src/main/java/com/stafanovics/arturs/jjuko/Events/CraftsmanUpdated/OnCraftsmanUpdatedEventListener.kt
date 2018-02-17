package com.stafanovics.arturs.jjuko.Events.CraftsmanUpdated

import com.stafanovics.arturs.jjuko.DataClasses.Craftsman

interface OnCraftsmanUpdatedEventListener {
    fun onEvent(craftsmen: List<Craftsman>)
}