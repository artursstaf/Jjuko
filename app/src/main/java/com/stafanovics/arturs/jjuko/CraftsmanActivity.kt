package com.stafanovics.arturs.jjuko

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.firebase.ui.auth.AuthUI
import com.stafanovics.arturs.jjuko.Adapters.AreasAdapter
import com.stafanovics.arturs.jjuko.Adapters.CraftsmanAdapter
import kotlinx.android.synthetic.main.activity_craftsman.*
import kotlinx.android.synthetic.main.activity_main.*

class CraftsmanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_craftsman)
        title = "Meistari"
        val craftsmanAdapter = CraftsmanAdapter(this, R.layout.craftsman_list_view, listOf("Jānis Ozoliņš", "Jēkabs Bērziņš", "Alberts Liepiņš", "Antons Kalniņš", "Valdis Jansons").sorted())
        craftsmanistView.adapter = craftsmanAdapter
    }
}
