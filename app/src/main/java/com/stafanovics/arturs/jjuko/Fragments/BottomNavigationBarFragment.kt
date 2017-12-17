package com.stafanovics.arturs.jjuko.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.stafanovics.arturs.jjuko.Activities.CraftsmanActivity
import com.stafanovics.arturs.jjuko.Activities.DealsList
import com.stafanovics.arturs.jjuko.Activities.MainActivity
import com.stafanovics.arturs.jjuko.R
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity


class BottomNavigationBarFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.bottom_navigation_bar_fragment, container, false)
        val favorites = view.find<TextView>(R.id.favorites_fragment_button)
        favorites.setOnClickListener { activity.startActivity<CraftsmanActivity>() }

        val start = view.find<TextView>(R.id.start_fragment_button)
        start.setOnClickListener { activity.startActivity<MainActivity>() }
        val deals = view.find<TextView>(R.id.deals_fragment_button)
        deals.setOnClickListener{ activity.startActivity<DealsList>()}
        return view
    }

}
