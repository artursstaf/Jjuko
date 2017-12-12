package com.stafanovics.arturs.jjuko

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.stafanovics.arturs.jjuko.Adapters.LocationAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Lokācija"
        val locationAdapter = LocationAdapter(this, R.layout.city_list_view, listOf("Liepāja", "Salaspilps", "Ogre"
        ,"Olaine", "Jūrmala", "Ventspils", "Jelgava", "Daugavpils", "Jēkabpils","Alūksne","Rēzekne", "Valmiera", "Cēsis", "Sigulda", "Kuldīga", "Tukums", "Talsi").sorted())
        areasListView.adapter = locationAdapter

    }

}
