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
            crafstsman_name.text = craftman.name + " " + craftman.surname
            val dateObj = Date(intent.getLongExtra("Date", 0))
            val df2 = SimpleDateFormat("dd/MM/yy")
            val str = df2.format(dateObj)
            date.text = str
            textClock.text = intent.getStringExtra("Time")
            create_deal_checkBox.visibility = View.GONE
            create_deal_cancel_button.setOnClickListener { onBackPressed() }

            create_deal_reserve_button.setOnClickListener {
                (application as MyApplication).deals.add(Deal(craftman, intent.getLongExtra("Date", 0),
                        intent.getStringExtra("Time"), false, description.text.toString()))
                longToast("Paldies! Rezervācja pieņemta")
                startActivity<DealsListActivity>()
            }
            //Deal exists
        }else{
            create_deal_foto_textView.visibility = View.GONE
            create_deal_foto_upload.visibility = View.GONE
            title = "Darijuma apskatīšana"
            val deal = (application as MyApplication).deals[intent.getIntExtra("Deal", 0)]
            crafstsman_name.text = deal.craftsman.name + " " + deal.craftsman.surname
            val dateObj = Date(deal.date)
            val df2 = SimpleDateFormat("dd/MM/yy")
            val str = df2.format(dateObj)
            date.text = str
            textClock.text = deal.time
            create_deal_checkBox.text = if(deal.accepted) "Apstirpināts" else "Neapstiprināts"
            create_deal_checkBox.isChecked = deal.accepted
            description.setText(deal.description, TextView.BufferType.EDITABLE)
            description.isEnabled = false
            description.setTextIsSelectable(true)
            create_deal_reserve_button.visibility = View.GONE
            create_deal_cancel_button.setOnClickListener {
                longToast("Darījums atcelts!")
                (application as MyApplication).deals.removeAt(intent.getIntExtra("Deal", 0))
                startActivity<DealsListActivity>()
            }
        }
    }
}
