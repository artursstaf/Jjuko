package com.stafanovics.arturs.jjuko.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.stafanovics.arturs.jjuko.Adapters.AreasAdapter
import com.stafanovics.arturs.jjuko.R
import kotlinx.android.synthetic.main.activity_areas.*

class AreasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_areas)
        title = "Jomas"
        val areaAdapter = AreasAdapter(this, R.layout.city_list_view, listOf("Elektorinstalācija", "Santehnika", "Sadzīves tehnika",
                "Datorremonts", "Remontdarbi", "Iekšdarbi", "Kosmētiskie darbi").sorted())
        areasListView.adapter = areaAdapter
    }

}
