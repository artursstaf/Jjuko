package com.stafanovics.arturs.jjuko.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.stafanovics.arturs.jjuko.DataClasses.Deal
import com.stafanovics.arturs.jjuko.MyApplication
import com.stafanovics.arturs.jjuko.R
import kotlinx.android.synthetic.main.activity_create_deal.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*

class CreateDealActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_deal)
        title = "Darijuma veidošana"
        //Coming from fresh deal
        if(intent.getIntExtra("Deal", -1) == -1) {
            val craftman = (application as MyApplication).craftsmen[intent.getIntExtra("Craftsman", 0)]
            text_create_deal_craftsman_full_name.text = craftman.name + " " + craftman.surname
            val dateObj = Date(intent.getLongExtra("Date", 0))
            val df2 = SimpleDateFormat("dd/MM/yy")
            val str = df2.format(dateObj)
            text_create_deal_date.text = str
            textClock.text = intent.getStringExtra("Time")
            check_create_deal.visibility = View.GONE
            button_create_deal_cancel.setOnClickListener { onBackPressed() }

            button_create_deal_reserve.setOnClickListener {
                (application as MyApplication).deals.add(Deal(craftman, intent.getLongExtra("Date", 0),
                        intent.getStringExtra("Time"), false, edit_create_deal_description.text.toString()))
                longToast("Paldies! Rezervācja pieņemta")
                startActivity<DealsListActivity>()
            }
            //Deal exists
        }else{
            text_create_deal_foto.visibility = View.GONE
            image_create_deal_foto_upload.visibility = View.GONE
            title = "Darijuma apskatīšana"
            val deal = (application as MyApplication).deals[intent.getIntExtra("Deal", 0)]
            text_create_deal_craftsman_full_name.text = deal.craftsman.name + " " + deal.craftsman.surname
            val dateObj = Date(deal.date)
            val df2 = SimpleDateFormat("dd/MM/yy")
            val str = df2.format(dateObj)
            text_create_deal_date.text = str
            textClock.text = deal.time
            check_create_deal.text = if (deal.accepted) "Apstirpināts" else "Neapstiprināts"
            check_create_deal.isChecked = deal.accepted
            edit_create_deal_description.setText(deal.description, TextView.BufferType.EDITABLE)
            edit_create_deal_description.isEnabled = false
            edit_create_deal_description.setTextIsSelectable(true)
            button_create_deal_reserve.visibility = View.GONE
            button_create_deal_cancel.setOnClickListener {
                longToast("Darījums atcelts!")
                (application as MyApplication).deals.removeAt(intent.getIntExtra("Deal", 0))
                startActivity<DealsListActivity>()
            }
        }
    }
}
