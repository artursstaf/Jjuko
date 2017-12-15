package com.stafanovics.arturs.jjuko

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
        favorites.setOnClickListener{activity.startActivity<CraftsmanActivity>()}
        return view
    }

    override fun onPause() {
        super.onPause()
    }

}
