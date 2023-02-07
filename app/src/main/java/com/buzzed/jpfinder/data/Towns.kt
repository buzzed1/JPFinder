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
            R.string.fairfield_estate,
            R.string.greenwood,
            R.string.long_bay,
            R.string.freeport,
            R.string.anchovy,
            R.string.granville,
            R.string.bay_shore,
            R.string.rose_hall,
            R.string.rhyne_park,
            R.string.cornwall_court,
            R.string.mount_salem,
            R.string.sunderland,
            R.string.adelphi,
            R.string.hopeton,
            R.string.leaders_ave,
            R.string.montepelier,
            R.string.west_village,
            R.string.rosemount,
            R.string.green_pond,
            R.string.little_river,
            R.string.somerton,
            R.string.lilliput,
            R.string.reading,
            R.string.wilshire,
            R.string.catherine_hall,
            R.string.coopers_hill,
            R.string.unity_hall

        )

    val stCatherineList = listOf(
        R.string.spanish_town,
        R.string.old_harbour,
        R.string.linstead,
        R.string.bog_walk,
        R.string.ewarton,
        R.string.old_harbour_bay,
        R.string.point_hill,
        R.string.above_rocks,
        R.string.riversdale,
        R.string.guys_hill,
        R.string.lluidas_vale,
        R.string.glengoffe,
        R.string.troja,
        R.string.central_village,
        R.string.sligoville,
        R.string.kitson_town

    )



    Log.d("parish", "$parish")
        //return montegoBayList.sorted().toSet()

    when(parish.lowercase()) {
        "st james" -> {return montegoBayList.sorted().toSet()}
        "st catherine" -> {return stCatherineList.sorted().toSet()}
    }


   return setOf()
}