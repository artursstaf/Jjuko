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
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*


class DealListAdapter(val ctx: Context, val resource: Int, val deals: List<Deal>) : ArrayAdapter<Deal>(ctx, resource, deals) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View?

        if(convertView == null){
            view = (ctx as Activity).layoutInflater.inflate(resource, parent,false)
        }else{
            view = convertView
        }
        view = view as View

        var name = view.findViewById<TextView>(R.id.text_deals_list_item_craftsman_full_name)
        var checkBox = view.findViewById<CheckBox>(R.id.check_deals_list_item_confirmation)
        var date = view.findViewById<TextView>(R.id.text_deals_list_item_date)
        var textClock = view.findViewById<TextView>(R.id.text_deals_list_item_clock)
        val deal = deals[position]

        name.text = deal.craftsman.name + " " + deal.craftsman.surname
        val dateObj = Date(deal.date)
        val df2 = SimpleDateFormat("dd/MM/yy")
        val str = df2.format(dateObj)
        date.text = str
        textClock.text = deal.time
        checkBox.isChecked = deal.accepted
        checkBox.text = if(deal.accepted) "Apstiprināts" else "Neapstiprināts"
        view.setOnClickListener {ctx.startActivity<CreateDealActivity>("Deal" to position)}
        return view
    }

}
