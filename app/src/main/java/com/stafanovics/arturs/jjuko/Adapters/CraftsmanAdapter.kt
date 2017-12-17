package com.stafanovics.arturs.jjuko.Adapters

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RatingBar
import android.widget.TextView
import com.stafanovics.arturs.jjuko.R
import com.stafanovics.arturs.jjuko.Activities.ViewCraftsmanActivity
import com.stafanovics.arturs.jjuko.DataClasses.Craftsman
import org.jetbrains.anko.*
import java.util.*

class CraftsmanAdapter(val ctx: Context,val resource: Int, val craftsmen: List<Craftsman>) : ArrayAdapter<Craftsman>(ctx,resource, craftsmen){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View?
        if(convertView == null){
            view = (context as Activity).layoutInflater.inflate(resource, parent, false)
        }else{
            view = convertView
        }


        val textView = view!!.findViewById<TextView>(R.id.craftsman)
        val craftsman = craftsmen[position]
        textView.text = craftsman.name + " " + craftsman.surname
        val ratingBar = view.findViewById<RatingBar>(R.id.rating)
        ratingBar.rating = craftsman.averageRating

        view.setOnClickListener { v ->
            ctx.startActivity<ViewCraftsmanActivity>("Craftsman" to position) }

        return view
    }
}