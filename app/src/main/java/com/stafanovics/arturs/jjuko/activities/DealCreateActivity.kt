package com.stafanovics.arturs.jjuko.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.stafanovics.arturs.jjuko.MyApplication
import com.stafanovics.arturs.jjuko.R
import com.stafanovics.arturs.jjuko.constants.INTENT_CALENDAR
import com.stafanovics.arturs.jjuko.constants.INTENT_CITY
import com.stafanovics.arturs.jjuko.constants.INTENT_CRAFTSMAN
import com.stafanovics.arturs.jjuko.constants.INTENT_SPECIALITY
import com.stafanovics.arturs.jjuko.dataClasses.City
import com.stafanovics.arturs.jjuko.dataClasses.Craftsman
import com.stafanovics.arturs.jjuko.dataClasses.Deal
import com.stafanovics.arturs.jjuko.dataClasses.Speciality
import com.stafanovics.arturs.jjuko.events.OnDealUpdatedEventListener
import kotlinx.android.synthetic.main.activity_create_deal.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.startActivity
import java.text.DateFormat
import java.text.DateFormat.getDateInstance
import java.text.DateFormat.getTimeInstance
import java.text.SimpleDateFormat
import java.util.*

class DealCreateActivity : AppCompatActivity() {

    private val mMyApplication by lazy { application as MyApplication }
    private val mCraftsman by lazy { intent.extras.get(INTENT_CRAFTSMAN) as Craftsman }
    private val mLocation by lazy { intent.extras.get(INTENT_CITY) as City }
    private val mSpeciality by lazy { intent.extras.get(INTENT_SPECIALITY) as Speciality }
    private val mCalendar by lazy { intent.extras.get(INTENT_CALENDAR) as? Calendar }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_deal)
        title = getString(R.string.title_deal_create)
        check_create_deal.visibility = View.GONE

        val dateObj = Date(mCalendar?.timeInMillis ?: 0)
        val df1 = SimpleDateFormat("HH:mm")
        val df2 = SimpleDateFormat("dd/MM/yy")

        text_create_deal_date.text = df2.format(dateObj)
        textClock.text = df1.format(dateObj)
        text_create_deal_craftsman_full_name.text = getString(R.string.msg_craftsman_full_name, mCraftsman.name, mCraftsman.surname)

        button_create_deal_cancel.setOnClickListener { onBackPressed() }
        button_create_deal_reserve.setOnClickListener {
            val desc = edit_create_deal_description.text.toString()
            //val id = UUID.randomUUID().toString()
            val deal = Deal(mMyApplication.userId, mCraftsman, mCalendar?.timeInMillis ?: 0, false, desc)
            mMyApplication.mFirestore.collection("Reservations").add(deal)

            longToast(getString(R.string.msg_reservation_accepted))
            startActivity<DealsListActivity>()
        }

    }
}
