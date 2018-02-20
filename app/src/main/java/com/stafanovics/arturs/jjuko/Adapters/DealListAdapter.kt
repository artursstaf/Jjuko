package com.stafanovics.arturs.jjuko.Adapters

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.stafanovics.arturs.jjuko.Activities.CreateDealActivity
import com.stafanovics.arturs.jjuko.DataClasses.Deal
import com.stafanovics.arturs.jjuko.R
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import java.text.DateFormat.getDateInstance
import java.util.*


class DealListAdapter(private val ctx: Context, private val resource: Int, private val deals: List<Deal>) : ArrayAdapter<Deal>(ctx, resource, deals) {

    companion object {
        const val INTENT_DEAL = "Deal"
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = convertView ?: (ctx as Activity).layoutInflater.inflate(resource, parent, false)

        val name = view.find<TextView>(R.id.text_deals_list_item_craftsman_full_name)
        val checkBox = view.find<CheckBox>(R.id.check_deals_list_item_confirmation)
        val date = view.find<TextView>(R.id.text_deals_list_item_date)
        val textClock = view.find<TextView>(R.id.text_deals_list_item_clock)

        val deal = deals[position]

        name.text = ctx.getString(R.string.msg_craftsman_full_name, deal.craftsman.name, deal.craftsman.surname)
        date.text = getDateInstance().format(Date(deal.date))

        textClock.text = deal.time
        checkBox.isChecked = deal.accepted
        checkBox.text = if (deal.accepted) ctx.getString(R.string.msg_label_checkbox_confirmed) else ctx.getString(R.string.msg_label_checkbox_unconfirmed)
        view.setOnClickListener { ctx.startActivity<CreateDealActivity>(INTENT_DEAL to position) }
        return view
    }

}
