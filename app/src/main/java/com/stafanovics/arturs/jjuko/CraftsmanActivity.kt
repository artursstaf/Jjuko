package com.stafanovics.arturs.jjuko

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.stafanovics.arturs.jjuko.Adapters.CraftsmanAdapter
import kotlinx.android.synthetic.main.activity_craftsman.*

class CraftsmanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_craftsman)
        title = "Meistari"
        val craftsmanAdapter = CraftsmanAdapter(this, R.layout.craftsman_list_view, listOf("Jānis Ozoliņš", "Jēkabs Bērziņš", "Alberts Liepiņš", "Antons Kalniņš", "Valdis Jansons").sorted())
        craftsmanListView.adapter = craftsmanAdapter
    }
}
