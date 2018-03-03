package com.stafanovics.arturs.jjuko.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import com.stafanovics.arturs.jjuko.Constants.*
import com.stafanovics.arturs.jjuko.DataClasses.City
import com.stafanovics.arturs.jjuko.DataClasses.Craftsman
import com.stafanovics.arturs.jjuko.DataClasses.Speciality
import com.stafanovics.arturs.jjuko.MyApplication
import com.stafanovics.arturs.jjuko.R
import kotlinx.android.synthetic.main.activity_craftsman.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.io.Serializable
import java.util.*


class CraftsmanActivity : AppCompatActivity() {

    private val mMyApplication by lazy { application as MyApplication }
    private val mCraftsman by lazy { intent.extras.get(INTENT_CRAFTSMAN) as Craftsman }
    private val mLocation by lazy { intent.extras.get(INTENT_CITY) as City }
    private val mSpeciality by lazy { intent.extras.get(INTENT_SPECIALITY) as Speciality }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_craftsman)
        title = getString(R.string.title_craftsman)
        text_craftsman_full_name.text = applicationContext.getString(R.string.msg_craftsman_full_name, mCraftsman.name, mCraftsman.surname)

        //Need Dynamic available time array
        val adapter = ArrayAdapter.createFromResource(ctx, R.array.dates_array, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_craftsman_time.adapter = adapter

        text_craftsman_description.text = mCraftsman.description
        setOnClickListener(button_craftsman_create_deal, calendar_craftsman.date)

        calendar_craftsman.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            val timeInMilis = calendar.timeInMillis
            setOnClickListener(button_craftsman_create_deal, timeInMilis)
        }

    }

    private fun setOnClickListener(view: View, time: Long) {
        view.setOnClickListener {
            if (spinner_craftsman_time.selectedItem.toString().isBlank()) {
                toast(ctx.getString(R.string.msg_choose_time))
            }

            startActivity<CreateDealActivity>(
                    INTENT_CRAFTSMAN to mCraftsman as Serializable,
                    INTENT_CITY to mLocation as Serializable,
                    INTENT_SPECIALITY to mSpeciality as Serializable,
                    INTENT_DATE to time, INTENT_TIME to spinner_craftsman_time.selectedItem.toString())
        }
    }
}
