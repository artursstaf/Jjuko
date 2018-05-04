package com.stafanovics.arturs.jjuko.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.stafanovics.arturs.jjuko.R
import com.stafanovics.arturs.jjuko.activities.CraftsmanListActivity
import com.stafanovics.arturs.jjuko.activities.DealsListActivity
import com.stafanovics.arturs.jjuko.activities.LocationListActivity
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity


class BottomNavigationBarFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_bottom_navigation_bar, container, false)
        val favorites = view.find<TextView>(R.id.text_fragment_bottom_nav_favorties)
        favorites.setOnClickListener { activity?.startActivity<CraftsmanListActivity>() }

        val start = view.find<TextView>(R.id.text_fragment_bottom_nav_start)
        start.setOnClickListener { activity?.startActivity<LocationListActivity>() }

        val deals = view.find<TextView>(R.id.text_fragment_bottom_nav_deals)
        deals.setOnClickListener { activity?.startActivity<DealsListActivity>() }
        return view
    }

}
