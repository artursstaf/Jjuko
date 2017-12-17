package com.stafanovics.arturs.jjuko.Adapters

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.stafanovics.arturs.jjuko.Activities.CraftsmanActivity
import com.stafanovics.arturs.jjuko.R
import org.jetbrains.anko.*
import org.jetbrains.anko.startActivity

class AreasAdapter(val ctx: Context,val resource: Int, val areas: List<String>) : ArrayAdapter<String>(ctx,resource, areas){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var retView: View
        if(convertView == null){
            retView = (context as Activity).layoutInflater.inflate(R.layout.city_list_view, parent, false)
        }else{
            retView = convertView
        }


        val textView: TextView = retView?.findViewById<TextView>(R.id.city) ?: TextView(ctx)
        val area = areas[position]
        textView.text = area
        retView.setOnClickListener { v ->
            ctx.toast(area)
            ctx.startActivity<CraftsmanActivity>("Area" to area) }

        return retView
    }
}