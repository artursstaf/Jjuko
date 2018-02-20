package com.stafanovics.arturs.jjuko.Adapters

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RatingBar
import android.widget.TextView
import com.stafanovics.arturs.jjuko.Activities.CraftsmanActivity
import com.stafanovics.arturs.jjuko.Constants.INTENT_CITY
import com.stafanovics.arturs.jjuko.Constants.INTENT_CRAFTSMAN
import com.stafanovics.arturs.jjuko.Constants.INTENT_SPECIALITY
import com.stafanovics.arturs.jjuko.DataClasses.City
import com.stafanovics.arturs.jjuko.DataClasses.Craftsman
import com.stafanovics.arturs.jjuko.DataClasses.Speciality
import com.stafanovics.arturs.jjuko.R
import org.jetbrains.anko.startActivity
import java.io.Serializable

class CraftsmanListAdapter(val ctx: Context, val resource: Int, val craftsmen: List<Craftsman>,
                           val location: City, val speciality: Speciality) : ArrayAdapter<Craftsman>(ctx, resource, craftsmen) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView
                ?: (context as Activity).layoutInflater.inflate(resource, parent, false)

        val textView = view.findViewById<TextView>(R.id.text_craftsman_list_item_full_name)
        val craftsman = craftsmen[position]

        textView.text = context.getString(R.string.msg_craftsman_full_name, craftsman.name, craftsman.surname)
        view.findViewById<RatingBar>(R.id.rating_craftsman_list_item_rating).rating = craftsman.averageRating


        view.setOnClickListener { _ ->
            ctx.startActivity<CraftsmanActivity>(INTENT_CRAFTSMAN to craftsman as Serializable,
                    INTENT_CITY to location as Serializable,
                    INTENT_SPECIALITY to speciality as Serializable)
        }

        return view
    }
}