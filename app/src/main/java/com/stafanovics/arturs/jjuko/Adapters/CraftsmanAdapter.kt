package com.stafanovics.arturs.jjuko.Adapters

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RatingBar
import android.widget.TextView
import com.stafanovics.arturs.jjuko.R
import com.stafanovics.arturs.jjuko.ViewCraftsmanActivity
import org.jetbrains.anko.*
import java.util.*

class CraftsmanAdapter(val ctx: Context,val resource: Int, val craftsmen: List<String>) : ArrayAdapter<String>(ctx,resource, craftsmen){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View?
        if(convertView == null){
            view = (context as Activity).layoutInflater.inflate(resource, parent, false)
        }else{
            view = convertView
        }


        val textView = view!!.findViewById<TextView>(R.id.craftsman)
        val craftsman = craftsmen[position]
        textView.text = craftsman
        val ratingBar = view.findViewById<RatingBar>(R.id.rating)
        val r = Random()
        val number = r.nextInt(6)
        ratingBar.rating = number.toFloat()

        view.setOnClickListener { v -> ctx.startActivity<ViewCraftsmanActivity>("Craftsman" to craftsman) }

        return view
    }
}