package com.stafanovics.arturs.jjuko.Activities

import android.app.Application
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.stafanovics.arturs.jjuko.Adapters.DealsAdapter
import com.stafanovics.arturs.jjuko.DataClasses.Craftsman
import com.stafanovics.arturs.jjuko.DataClasses.Deal
import com.stafanovics.arturs.jjuko.MyApplication
import com.stafanovics.arturs.jjuko.R
import kotlinx.android.synthetic.main.activity_deals_list.*
import org.jetbrains.anko.ctx

class DealsList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deals_list)
        title = "Darījumi"
        val deals = (application as MyApplication).deals
        dealsListView.adapter = DealsAdapter(ctx, R.layout.deals_list_view, deals)
    }
}
