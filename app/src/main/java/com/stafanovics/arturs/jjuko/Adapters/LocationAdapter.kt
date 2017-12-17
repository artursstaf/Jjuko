package com.stafanovics.arturs.jjuko.Adapters

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.stafanovics.arturs.jjuko.Activities.AreasActivity
import com.stafanovics.arturs.jjuko.R
import org.jetbrains.anko.*
import org.jetbrains.anko.startActivity

class LocationAdapter(val ctx: Context,val resource: Int, val cities: List<String>) : ArrayAdapter<String>(ctx, resource, cities){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View? = null
        if(convertView == null){
            view = (context as Activity).layoutInflater.inflate(R.layout.city_list_view, parent, false)
        }else{
            view = convertView
        }

        val textView = view!!.findViewById<TextView>(R.id.city)
        val city = cities[position]
        textView.text = city
        view.setOnClickListener { v ->
            ctx.toast(city)
            ctx.startActivity<AreasActivity>("City" to city) }
        return view
    }
}