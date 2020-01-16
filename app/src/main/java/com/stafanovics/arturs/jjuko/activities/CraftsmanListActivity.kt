package com.stafanovics.arturs.jjuko.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.stafanovics.arturs.jjuko.MyApplication
import com.stafanovics.arturs.jjuko.R
import com.stafanovics.arturs.jjuko.adapters.CraftsmanListAdapter
import com.stafanovics.arturs.jjuko.constants.INTENT_CITY
import com.stafanovics.arturs.jjuko.constants.INTENT_SPECIALITY
import com.stafanovics.arturs.jjuko.dataClasses.City
import com.stafanovics.arturs.jjuko.dataClasses.Craftsman
import com.stafanovics.arturs.jjuko.dataClasses.Speciality
import com.stafanovics.arturs.jjuko.events.OnCraftsmanUpdatedEventListener
import kotlinx.android.synthetic.main.activity_craftsman_list.*

class CraftsmanListActivity : AppCompatActivity() {

    private val mListAdapter by lazy {
        CraftsmanListAdapter(this, R.layout.craftsman_list_item,
                ArrayList(), mFilterCity, mFilterSpeciality)
    }
    private val mMyApplication by lazy { application as MyApplication }
    private val mFilterSpeciality by lazy { intent.extras.get(INTENT_SPECIALITY) as Speciality }
    private val mFilterCity by lazy { intent.extras.get(INTENT_CITY) as City }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_craftsman_list)
        title = getString(R.string.title_craftsman_list)
        list_craftsman_list.adapter = mListAdapter
    }

    override fun onStart() {
        mListAdapter.addAll(filterCity(filterCraftsmen(mMyApplication.craftsmen)))
        mMyApplication.addOnCraftsmanUpdateListener(mSpecialityListener)
        super.onStart()
    }

    override fun onStop() {
        mListAdapter.clear()
        mMyApplication.removeOnCraftsmanUpdateListener(mSpecialityListener)
        super.onStop()
    }

    private fun filterCraftsmen(craftsmen: List<Craftsman>) =
            craftsmen.mapNotNull { if (it.speciality.contains(mFilterSpeciality)) it else null }

    private fun filterCity(craftsmen: List<Craftsman>) =
            craftsmen.filter { it.locations.contains(mFilterCity) }

    private val mSpecialityListener = object : OnCraftsmanUpdatedEventListener {
        override fun onEvent(craftsmen: List<Craftsman>) {
            mListAdapter.clear()
            mListAdapter.addAll(filterCraftsmen(craftsmen))
        }
    }
}
