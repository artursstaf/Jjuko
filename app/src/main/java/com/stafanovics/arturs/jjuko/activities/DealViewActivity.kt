package com.stafanovics.arturs.jjuko.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.stafanovics.arturs.jjuko.MyApplication
import com.stafanovics.arturs.jjuko.R
import com.stafanovics.arturs.jjuko.constants.INTENT_DEAL
import com.stafanovics.arturs.jjuko.dataClasses.Deal
import kotlinx.android.synthetic.main.activity_create_deal.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.startActivity

class DealViewActivity : AppCompatActivity() {

    private val mMyApplication by lazy { application as MyApplication }
    private val mDeal by lazy { intent.extras.get(INTENT_DEAL) as Deal }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_deal)
        title = getString(R.string.title_deal_view)

        text_create_deal_foto.visibility = View.GONE
        image_create_deal_foto_upload.visibility = View.GONE

//        text_create_deal_craftsman_full_name.text = deal.craftsman.name + " " + deal.craftsman.surname
//        val dateObj = Date(deal.date)
//        val df2 = SimpleDateFormat("dd/MM/yy")
//        val str = df2.format(dateObj)
//        text_create_deal_date.text = str
//        textClock.text = deal.time
//        check_create_deal.text = if (deal.accepted) "Apstirpināts" else "Neapstiprināts"
//        check_create_deal.isChecked = deal.accepted
//        edit_create_deal_description.setText(deal.description, TextView.BufferType.EDITABLE)
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
