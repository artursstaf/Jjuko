package com.stafanovics.arturs.jjuko.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.stafanovics.arturs.jjuko.MyApplication
import com.stafanovics.arturs.jjuko.R
import com.stafanovics.arturs.jjuko.adapters.CraftsmanListAdapter
import com.stafanovics.arturs.jjuko.adapters.DealListAdapter
import com.stafanovics.arturs.jjuko.dataClasses.Deal
import com.stafanovics.arturs.jjuko.events.OnDealUpdatedEventListener
import kotlinx.android.synthetic.main.activity_deals_list.*
import org.jetbrains.anko.ctx

class DealsListActivity : AppCompatActivity() {

    private val mMyApplication by lazy { application as MyApplication }

    private val mListAdapter by lazy {
        DealListAdapter(this, R.layout.deals_list_item,
                ArrayList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deals_list)
        title = getString(R.string.title_deal_list)
        list_deals_list.adapter = mListAdapter
    }

    override fun onStart() {
        mListAdapter.addAll(mMyApplication.deals)
        mMyApplication.addOnDealpdateListener(mDealListener)
        super.onStart()
    }

    override fun onStop() {
        mListAdapter.clear()
        mMyApplication.removeOnDealUpdateListener(mDealListener)
        super.onStop()
    }

    private val mDealListener = object : OnDealUpdatedEventListener {
        override fun onEvent(deal: List<Deal>) {
            (list_deals_list.adapter as DealListAdapter).clear()
            (list_deals_list.adapter as DealListAdapter).addAll(deal)
        }
    }
}
