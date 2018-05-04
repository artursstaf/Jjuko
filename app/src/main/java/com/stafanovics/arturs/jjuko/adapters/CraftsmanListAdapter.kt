package com.stafanovics.arturs.jjuko.adapters

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RatingBar
import android.widget.TextView
import com.stafanovics.arturs.jjuko.R
import com.stafanovics.arturs.jjuko.activities.CraftsmanActivity
import com.stafanovics.arturs.jjuko.constants.INTENT_CITY
import com.stafanovics.arturs.jjuko.constants.INTENT_CRAFTSMAN
import com.stafanovics.arturs.jjuko.constants.INTENT_SPECIALITY
import com.stafanovics.arturs.jjuko.dataClasses.City
import com.stafanovics.arturs.jjuko.dataClasses.Craftsman
import com.stafanovics.arturs.jjuko.dataClasses.Speciality
import org.jetbrains.anko.startActivity
import java.io.Serializable

class CraftsmanListAdapter(private val ctx: Context, private val resource: Int, private val craftsmen: List<Craftsman>,
                           private val location: City, private val speciality: Speciality) : ArrayAdapter<Craftsman>(ctx, resource, craftsmen) {

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