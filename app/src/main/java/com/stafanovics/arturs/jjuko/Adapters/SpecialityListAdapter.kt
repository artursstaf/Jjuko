package com.stafanovics.arturs.jjuko.Adapters

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.stafanovics.arturs.jjuko.Activities.CraftsmanListActivity
import com.stafanovics.arturs.jjuko.DataClasses.Speciality
import com.stafanovics.arturs.jjuko.R
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.io.Serializable

class SpecialityListAdapter(val ctx: Context, val resource: Int, val areas: List<Speciality>) : ArrayAdapter<Speciality>(ctx, resource, areas) {
    companion object {
        const val INTENT_SPECIALITY = "Speciality"
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView
                ?: (context as Activity).layoutInflater.inflate(R.layout.location_list_item, parent, false)

        val textView: TextView = view.findViewById<TextView>(R.id.text_speciality_list_item)
                ?: TextView(ctx)

        val speciality: Speciality = areas[position]

        textView.text = speciality.name
        view.setOnClickListener { _ ->
            ctx.toast(speciality.name)
            ctx.startActivity<CraftsmanListActivity>(INTENT_SPECIALITY to speciality as Serializable)
        }

        return view
    }
}