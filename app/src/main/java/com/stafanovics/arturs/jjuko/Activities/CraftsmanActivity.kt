package com.stafanovics.arturs.jjuko.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.stafanovics.arturs.jjuko.MyApplication
import com.stafanovics.arturs.jjuko.R
import kotlinx.android.synthetic.main.activity_view_craftsman.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity
import java.util.*


class CraftsmanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_craftsman)
        title = "Meistars"
        val craftman = (application as MyApplication).craftsmen[intent.getIntExtra("Craftsman",0)]
        crafstsman_name.text = craftman.name + " " + craftman.surname
        val adapter = ArrayAdapter.createFromResource(ctx, R.array.dates_array, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        TEXT_VIEW.text = craftman.description
        craftsman_view_button.setOnClickListener{ startActivity<CreateDealActivity>(
                "Craftsman" to intent.getIntExtra("Craftsman",0), "Date" to calendarView.date, "Time" to spinner.selectedItem.toString()) }

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            val timeInMilis = calendar.timeInMillis

            craftsman_view_button.setOnClickListener{ startActivity<CreateDealActivity>(
                    "Craftsman" to intent.getIntExtra("Craftsman",0), "Date" to timeInMilis, "Time" to spinner.selectedItem.toString()) }}
        }

}
