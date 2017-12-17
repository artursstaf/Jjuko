package com.stafanovics.arturs.jjuko

import android.app.Application
import com.stafanovics.arturs.jjuko.DataClasses.Craftsman
import com.stafanovics.arturs.jjuko.DataClasses.Deal


class MyApplication : Application() {
    val craftsmen = ArrayList<Craftsman>()
    val deals = ArrayList<Deal>()
    init{

        craftsmen.add( Craftsman(5.0f,"Jānis", "Bērziņš", "21234567", "Ļoti labs", listOf("Olaine", "Valmiera"), listOf("Datorremonts, Elektroinstalācija")))
        craftsmen.add(Craftsman(4.5f, "Alberts", "Liepiņš", "21234567", "Ļoti labs", listOf("Olaine", "Valmiera"), listOf("Datorremonts", "Elektroinstalācija")))
        craftsmen.add(Craftsman(4.0f, "Jānis", "Ozoliņš", "21234567", "Ļoti labs", listOf("Olaine", "Valmiera"), listOf("Datorremonts", "Elektroinstalācija")))
        craftsmen.add(Craftsman(4.0f, "Jēkabs", "Bērziņš", "21234567", "Ļoti labs", listOf("Olaine", "Valmiera"), listOf("Datorremonts", "Elektroinstalācija")))
        craftsmen.add(Craftsman(3.5f, "Valdis", "Jansons", "21234567", "Ļoti labs", listOf("Olaine", "Valmiera"), listOf("Datorremonts", "Elektroinstalācija")))
        craftsmen.add(Craftsman(3.5f, "Kaspars", "Skuja", "21234567", "Ļoti labs", listOf("Olaine", "Valmiera"), listOf("Datorremonts", "Elektroinstalācija")))

        deals.add(Deal(craftsmen[0],1513527060500,"18:20",true, "Jāsalabo krāns"))
        deals.add(Deal(craftsmen[1], 1513529412, "12:10", false, "Jānokrāso tapetes"))
    }
}