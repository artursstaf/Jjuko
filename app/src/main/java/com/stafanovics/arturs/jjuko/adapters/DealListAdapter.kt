package com.stafanovics.arturs.jjuko.adapters

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.stafanovics.arturs.jjuko.R
import com.stafanovics.arturs.jjuko.activities.DealCreateActivity
import com.stafanovics.arturs.jjuko.activities.DealViewActivity
import com.stafanovics.arturs.jjuko.constants.INTENT_DEAL
import com.stafanovics.arturs.jjuko.dataClasses.Deal
import kotlinx.android.synthetic.main.activity_create_deal.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import java.text.DateFormat
import java.text.DateFormat.getDateInstance
import java.text.DateFormat.getTimeInstance
import java.text.SimpleDateFormat
import java.util.*


class DealListAdapter(private val ctx: Context, private val resource: Int, private val deals: List<Deal>) : ArrayAdapter<Deal>(ctx, resource, deals) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = convertView ?: (ctx as Activity).layoutInflater.inflate(resource, parent, false)

        val name = view.find<TextView>(R.id.text_deals_list_item_craftsman_full_name)
        val checkBox = view.find<CheckBox>(R.id.check_deals_list_item_confirmation)
        val date = view.find<TextView>(R.id.text_deals_list_item_date)
        val textClock = view.find<TextView>(R.id.text_deals_list_item_clock)

        val deal = deals[position]

        name.text = ctx.getString(R.string.msg_craftsman_full_name, deal.craftsman.name, deal.craftsman.surname)
        val dateObj = Date(deal.date)
        val df1 = SimpleDateFormat("HH:mm")
        val df2 = SimpleDateFormat("dd/MM/yy")
        date.text = df2.format(dateObj)
        textClock.text = df1.format(dateObj)

        checkBox.isChecked = deal.accepted

        checkBox.text = if (deal.accepted) ctx.getString(R.string.msg_label_checkbox_confirmed)
        else ctx.getString(R.string.msg_label_checkbox_unconfirmed)

        view.setOnClickListener { ctx.startActivity<DealViewActivity>(INTENT_DEAL to position) }
        return view
    }

}
