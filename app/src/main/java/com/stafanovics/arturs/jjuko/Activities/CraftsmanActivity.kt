package com.stafanovics.arturs.jjuko.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.stafanovics.arturs.jjuko.Adapters.CraftsmanAdapter
import com.stafanovics.arturs.jjuko.MyApplication
import com.stafanovics.arturs.jjuko.R
import kotlinx.android.synthetic.main.activity_craftsman.*

class CraftsmanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_craftsman)
        title = "Meistari"
        val craftsmen = (application as MyApplication).craftsmen
        val craftsmanAdapter = CraftsmanAdapter(this, R.layout.craftsman_list_view, craftsmen)
        craftsmanListView.adapter = craftsmanAdapter
    }
}
