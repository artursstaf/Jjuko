package com.stafanovics.arturs.jjuko

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.stafanovics.arturs.jjuko.Adapters.AreasAdapter

import kotlinx.android.synthetic.main.activity_main.*

class AreasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_areas)
        title = "Jomas"
        val areaAdapter = AreasAdapter(this, R.layout.city_list_view, listOf("Elektorinstalācija", "Santehnika", "Sadzīves tehnika", "Datorremonts").sorted())
        areasListView.adapter = areaAdapter
    }
}
