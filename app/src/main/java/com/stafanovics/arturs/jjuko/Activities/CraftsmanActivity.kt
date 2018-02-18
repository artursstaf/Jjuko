package com.stafanovics.arturs.jjuko.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import com.stafanovics.arturs.jjuko.Adapters.CraftsmanListAdapter
import com.stafanovics.arturs.jjuko.DataClasses.Craftsman
import com.stafanovics.arturs.jjuko.MyApplication
import com.stafanovics.arturs.jjuko.R
import kotlinx.android.synthetic.main.activity_craftsman.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity
import java.util.*


class CraftsmanActivity : AppCompatActivity() {

    private val mMyApplication by lazy { application as MyApplication }
    private val mCraftsman by lazy { intent.extras.get(CraftsmanListAdapter.INTENT_CRAFTSMAN) as Craftsman }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_craftsman)
        title = getString(R.string.title_craftsman)


        crafstsman_name.text = applicationContext.getString(R.string.msg_craftsman_full_name, mCraftsman.name, mCraftsman.surname)

        //Need Dynamic available time array
        val adapter = ArrayAdapter.createFromResource(ctx, R.array.dates_array, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        text_description.text = mCraftsman.description
        setOnClickListener(craftsman_view_button, calendarView.date)

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            val timeInMilis = calendar.timeInMillis
            setOnClickListener(craftsman_view_button, timeInMilis)
        }
    }

    private fun setOnClickListener(view: View, time: Long) {
        view.setOnClickListener {
            startActivity<CreateDealActivity>(
                    "Craftsman" to mCraftsman, "Date" to time, "Time" to spinner.selectedItem.toString())
        }
    }
}
