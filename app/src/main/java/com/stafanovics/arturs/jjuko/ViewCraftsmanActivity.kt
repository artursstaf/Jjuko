package com.stafanovics.arturs.jjuko

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_craftsman.*

class ViewCraftsmanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_craftsman)
        title = "Meistars"
        crafstsman_name.text = intent.getStringExtra("Craftsman")

    }
}
