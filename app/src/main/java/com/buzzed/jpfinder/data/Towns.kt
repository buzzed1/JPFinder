package com.buzzed.jpfinder.data

import android.util.Log
import com.buzzed.jpfinder.R


fun towns(parish: String): Set<Int> {



            val montegoBayList = listOf(
            R.string.glendevon,
            R.string.norwood,
            R.string.paridise,
            R.string.westgreen,
            R.string.iron_shore,
            R.string.irwin,
            R.string.lethe,
            R.string.coral_gardens,
            R.string.bogue_village,
            R.string.goodwill,
            R.string.gloucester_ave,
            R.string.mt_horeb,
            R.string.westgate,
            R.string.bogue_heights,
            R.string.mango_walk,
            R.string.springfield,
            R.string.cambridge,
            /*"Fairfield Estate",
        "Greenwood",
        "Long Bay",
        "Freeport",
        "Anchovy",
        "Granville",
        "Bay Shore",
        "Rose Hall",
        "Rhyne Park",
        "Cornwall Courts",
        "Mount Salem",
        "Sunderland",
        "Adelphi",
        "Hopeton",
        "Leaders Ave",
        "Montepelier",
        "West Village",
        "Rosemount",
        "Green Pond",
        "Little River",
        "Somerton",
        "Lilliput",
        "Reading",
        "Wilshire",
        "Catherine Hall",
        "Coopers Hill",
        "Unity Hall"*/

        )

    val stCatherineList = listOf(
        "Spanish Town",
        "Old Harbour",
        "Linstead",
        "Bog Walk",
        "Ewarton",
        "Old Harbour Bay",
        "Point Hill",
        "Above Rocks",
        "Riversdale",
        "Guys Hill",
        "Lluidas Vale",
        "Glengoffe",
        "Troja",
        "Central Village",
        "Sligoville",
        "Kitson Town"

    )



    Log.d("parish", "$parish")
        //return montegoBayList.sorted().toSet()

    when(parish.lowercase()) {
        "st james" -> {return montegoBayList.sorted().toSet()}
        "st catherine" -> {return stCatherineList.sorted().toSet()}
    }


   return setOf()
}