package com.stafanovics.arturs.jjuko.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.stafanovics.arturs.jjuko.Activities.CraftsmanListActivity
import com.stafanovics.arturs.jjuko.Activities.DealsListActivity
import com.stafanovics.arturs.jjuko.Activities.LocationListActivity
import com.stafanovics.arturs.jjuko.R
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity


class BottomNavigationBarFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_bottom_navigation_bar, container, false)
        val favorites = view.find<TextView>(R.id.favorites_fragment_button)
        favorites.setOnClickListener { activity?.startActivity<CraftsmanListActivity>() }

        val start = view.find<TextView>(R.id.start_fragment_button)
        start.setOnClickListener { activity?.startActivity<LocationListActivity>() }

        val deals = view.find<TextView>(R.id.deals_fragment_button)
        deals.setOnClickListener { activity?.startActivity<DealsListActivity>() }
        return view
    }

}
