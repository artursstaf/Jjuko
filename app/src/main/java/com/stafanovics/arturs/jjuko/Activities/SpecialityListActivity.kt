package com.stafanovics.arturs.jjuko.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.stafanovics.arturs.jjuko.Adapters.LocationlistAdapter
import com.stafanovics.arturs.jjuko.Adapters.SpecialityListAdapter
import com.stafanovics.arturs.jjuko.DataClasses.City
import com.stafanovics.arturs.jjuko.DataClasses.Craftsman
import com.stafanovics.arturs.jjuko.DataClasses.Speciality
import com.stafanovics.arturs.jjuko.Events.CraftsmanUpdated.OnCraftsmanUpdatedEventListener
import com.stafanovics.arturs.jjuko.MyApplication
import com.stafanovics.arturs.jjuko.R
import kotlinx.android.synthetic.main.activity_speciality.*

class SpecialityListActivity : AppCompatActivity() {

    private val mListAdapter by lazy { SpecialityListAdapter(this, R.layout.city_list_view, ArrayList<Speciality>()) }
    private val mMyApplication by lazy { application as MyApplication }
    private lateinit var mFilterCity: City

    fun getSpecialitiesFromCraftsmen(list: List<Craftsman>): Collection<Speciality> {
        val listsOfSpecialities: List<List<Speciality>> = list.mapNotNull { if (it.locations.contains(mFilterCity)) it.speciality else null }
        val specialities = LinkedHashSet<Speciality>()
        listsOfSpecialities.forEach { it.forEach { specialities.add(it) } }
        return specialities
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speciality)
        title = getString(R.string.title_area_list)
        areasListView.adapter = mListAdapter

        mFilterCity = intent.extras.get(LocationlistAdapter.INTENT_CITY) as City
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

}
