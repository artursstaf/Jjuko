package com.stafanovics.arturs.jjuko.adapters

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.stafanovics.arturs.jjuko.R
import com.stafanovics.arturs.jjuko.activities.SpecialityListActivity
import com.stafanovics.arturs.jjuko.constants.INTENT_CITY
import com.stafanovics.arturs.jjuko.dataClasses.City
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.io.Serializable

class LocationlistAdapter(private val ctx: Context, resource: Int, private val cities: List<City>) : ArrayAdapter<City>(ctx, resource, cities) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View = convertView
                ?: (context as Activity).layoutInflater.inflate(R.layout.location_list_item, parent, false)

        val textView = view.findViewById<TextView>(R.id.text_speciality_list_item)
        val city: City = cities[position]
        textView.text = city.name
        view.setOnClickListener { _ ->
            ctx.toast(city.name)
            ctx.startActivity<SpecialityListActivity>(INTENT_CITY to city as Serializable)
        }

        return view
    }
}