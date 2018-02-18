package com.stafanovics.arturs.jjuko.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.stafanovics.arturs.jjuko.Adapters.DealListAdapter
import com.stafanovics.arturs.jjuko.MyApplication
import com.stafanovics.arturs.jjuko.R
import kotlinx.android.synthetic.main.activity_deals_list.*
import org.jetbrains.anko.ctx

class DealsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deals_list)
        title = getString(R.string.title_deal_list)

        val deals = (application as MyApplication).deals

        dealsListView.adapter = DealListAdapter(ctx, R.layout.deals_list_item, deals)
    }
}
