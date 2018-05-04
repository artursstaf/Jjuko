package com.stafanovics.arturs.jjuko.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.stafanovics.arturs.jjuko.MyApplication
import com.stafanovics.arturs.jjuko.R
import com.stafanovics.arturs.jjuko.adapters.SpecialityListAdapter
import com.stafanovics.arturs.jjuko.constants.INTENT_CITY
import com.stafanovics.arturs.jjuko.dataClasses.City
import com.stafanovics.arturs.jjuko.dataClasses.Craftsman
import com.stafanovics.arturs.jjuko.dataClasses.Speciality
import com.stafanovics.arturs.jjuko.events.OnCraftsmanUpdatedEventListener
import kotlinx.android.synthetic.main.activity_speciality_list.*

class SpecialityListActivity : AppCompatActivity() {

    private val mListAdapter by lazy { SpecialityListAdapter(this, R.layout.speciality_list_item, ArrayList(), mFilterCity) }
    private val mMyApplication by lazy { application as MyApplication }
    private val mFilterCity by lazy { intent.extras.get(INTENT_CITY) as City }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speciality_list)
        title = getString(R.string.title_area_list)

        list_speciality.adapter = mListAdapter
    }

    override fun onStart() {
        mListAdapter.addAll(getSpecialitiesFromCraftsmen(mMyApplication.craftsmen))
        mMyApplication.addOnCraftsmanUpdateListener(mSpecialityListener)
        super.onStart()
    }

    override fun onStop() {
        mListAdapter.clear()
        mMyApplication.removeOnCraftsmanUpdateListener(mSpecialityListener)
        super.onStop()
    }

    private val mSpecialityListener = object : OnCraftsmanUpdatedEventListener {
        override fun onEvent(craftsmen: List<Craftsman>) {
            mListAdapter.clear()
            mListAdapter.addAll(getSpecialitiesFromCraftsmen(craftsmen))
        }

    }

    private fun getSpecialitiesFromCraftsmen(list: List<Craftsman>): Collection<Speciality> {
        val listsOfSpecialities: List<List<Speciality>> = list.mapNotNull { if (it.locations.contains(mFilterCity)) it.speciality else null }
        val specialities = LinkedHashSet<Speciality>()
        listsOfSpecialities.forEach { it.forEach { specialities.add(it) } }
        return specialities
    }

}
