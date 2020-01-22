package com.stafanovics.arturs.jjuko.events

import com.stafanovics.arturs.jjuko.dataClasses.Craftsman
import com.stafanovics.arturs.jjuko.dataClasses.Deal

interface OnDealUpdatedEventListener {
    fun onEvent(deal: List<Deal>)
}