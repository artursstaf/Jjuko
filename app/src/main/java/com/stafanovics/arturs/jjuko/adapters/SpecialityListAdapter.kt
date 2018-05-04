package com.stafanovics.arturs.jjuko.adapters

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.stafanovics.arturs.jjuko.R
import com.stafanovics.arturs.jjuko.activities.CraftsmanListActivity
import com.stafanovics.arturs.jjuko.constants.INTENT_CITY
import com.stafanovics.arturs.jjuko.constants.INTENT_SPECIALITY
import com.stafanovics.arturs.jjuko.dataClasses.City
import com.stafanovics.arturs.jjuko.dataClasses.Speciality
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.io.Serializable

class SpecialityListAdapter(private val ctx: Context, val resource: Int, private val areas: List<Speciality>, private val location: City) : ArrayAdapter<Speciality>(ctx, resource, areas) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView
                ?: (context as Activity).layoutInflater.inflate(R.layout.location_list_item, parent, false)

        val textView: TextView = view.findViewById(R.id.text_speciality_list_item)
                ?: TextView(ctx)

        val speciality: Speciality = areas[position]

        textView.text = speciality.name
        view.setOnClickListener { _ ->
            ctx.toast(speciality.name)
            ctx.startActivity<CraftsmanListActivity>(INTENT_SPECIALITY to speciality as Serializable,
                    INTENT_CITY to location as Serializable)
        }

        return view
    }
}