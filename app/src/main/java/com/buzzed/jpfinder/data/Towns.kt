package com.buzzed.jpfinder.data

import android.util.Log
import com.buzzed.jpfinder.R


fun towns(parish: String): Set<String> {



            val montegoBayList = listOf(
                "Adelphi",
                "Albion",
                "Anchorage",
                "Anchovy",
                "Barnett Heights",
                "Barnett view",
                "Bickersteth",
                "Bogue Heights",
                "Bogue Hill",
                "Bogue Village",
                "Brandon Hill",
                "Cambridge",
                "Cambrose",
                "Catherine Hall",
                "Catherine Mount",
                "Chatham",
                "Claude Clarke",
                "Coral Gardens",
                "Cornwall Courts",
                "Cornwall Gardens",
                "Dumfries",
                "Dunbar",
                "Elderslie",
                "Estuary",
                "Fairfield",
                "Farm Heights",
                "Flamstead",
                "Flankers",
                "Freeport",
                "Garlands",
                "Glendevon",
                "Goodwill",
                "Granville",
                "Great River",
                "Green Pond",
                "Greenwood",
                "Hampton",
                "Hurlock",
                "Ironshore",
                "Irwin",
                "Irwin Meadows",
                "Irwindale",
                "Johns Hall",
                "Kempshot",
                "Leaders",
                "Lethe",
                "Lilliput",
                "Little River",
                "Lottery",
                "Mafoota",
                "Maldon",
                "Mango Walk",
                "Maroon Town",
                "Montego Bay",
                "Montego Hills",
                "Montpelier",
                "Morgan Road",
                "Mount Horeb",
                "Mount Salem",
                "Mountpelier",
                "Norwood",
                "Norwood",
                "Orange",
                "Paradise",
                "Pitfour",
                "Point",
                "Porto Bello",
                "Queens Drive",
                "Reading",
                "Retirement",
                "Rhyne Park",
                "Roehampton",
                "Rose Hall",
                "Rose Heights",
                "Rosehall",
                "Rosemount",
                "Salt Spring",
                "Sign",
                "Somerton",
                "Spring Farm",
                "Spring Gardens",
                "Spring Mount",
                "Springfield",
                "Sunderland",
                "Torado Heights",
                "Tucker",
                "Unity Hall",
                "Welcome Hall",
                "West Village",
                "Westgate",
                "Westgate Hills",
                "Westgreen",
                "White House",
                "White Sands",
                "Wiltshire",


                )

    val stCatherineList = listOf(
        "Above Rocks",
        "Bog Walk",
        "Central Village",
        "Ewarton",
        "Glengoffe",
        "Guys Hill",
        "Kitson Tow",
        "Linstead",
        "Lluidas Vale",
        "Old Harbour",
        "Old Harbour Bay",
        "Point Hill",
        "Riversdale",
        "Sligoville",
        "Spanish Town",
        "Troja"

    )

   val clarendonList = listOf(
       "Alston",
       "Ashley",
       "Birds Hill",
       "Borobridge",
       "Bushy Park",
       "Canaan Heights",
       "Chapleton",
       "Chateau",
       "Coates Pen",
       "Coffee Piece",
       "Colonels Ridge",
       "Coxwain",
       "Crawl River",
       "Crofts Hill",
       "Crooked River",
       "Gimme-me-bit",
       "Glenmuir road",
       "Gloucester",
       "Grantham",
       "Gravel hill",
       "Green bottom",
       "Gregory",
       "Halse hall",
       "Harriston town",
       "Hartwell gardens",
       "Hayes",
       "Hopefield",
       "Howella content",
       "Inglewood",
       "Jackson street",
       "James hill",
       "Kellits",
       "Lionel town",
       "Longlook",
       "Longville",
       "May Pen",
       "Menie",
       "Milk river",
       "Mineral heights",
       "Mitchell town",
       "Mocho",
       "Moneymusk",
       "Moores",
       "Moravia",
       "Morgans pass",
       "Mew bowers",
       "New longville",
       "New paisely",
       "Nine turns",
       "Osbourne store",
       "Palmers cross",
       "Peckham",
       "Pennants",
       "Portland cottage",
       "Race course",
       "Richmond park",
       "Rock river",
       "Rockford",
       "Rocky point",
       "Sandy bay",
       "Sanguinetti",
       "Scotts pass",
       "Smithville",
       "Spalding",
       "St georges",
       "Stewarton",
       "Thompson town",
       "Tollgate",
       "Top hill",
       "Treadlight",
       "Trout hall",
       "Tweedside",
       "Twin palms",
       "Witney",
       "Wood hall",
       "York town"

    )

    val trelawnyList = listOf(
        "Albert Town",
        "Bounty Hall",
        "Browns Town",
        "Bunkers Hill",
        "Carrick Foyle",
        "Clarks Town",
        "Cold Spring",
        "Coral Spring",
        "Daniel Town",
        "Davis Pen",
        "Deeside",
        "Dromilly District",
        "Duanvale",
        "Duncans",
        "Falmouth",
        "Falmouth Gdns",
        "Flamingo Beach",
        "Florence Hall",
        "Gravel Hill",
        "Green Park",
        "Greenwood",
        "Hague",
        "Jackson Town",
        "Joe Hut P.A.",
        "Long Pond",
        "Lorrimers",
        "Lowe River",
        "Martha Brae",
        "Mount Carey",
        "New Haven Heights",
        "New Hope",
        "Perth town",
        "Refuge P.A.",
        "Retreat Heights",
        "Rio Bueno",
        "Rock Spring",
        "Sal Marsh",
        "Salt Marsh",
        "Sawyers District",
        "Sherwood Content",
        "Spicy Hill District",
        "Spring Gdns",
        "Springfield",
        "St Ann's Bay",
        "Stettin",
        "Stewart Castle",
        "Stewart Town",
        "Trelawny",
        "Troy",
        "Vanzie Lands",
        "Wait-a-Bit",
        "Wakefield",
        "Wakefield Dist",
        "Warsop",
        "Wiltshire",
        "Wire Fence",

        )


    val stAnnList = listOf(
        "Aboukir",
        "Alderton",
        "Alexandria",
        "Alva District",
        "Bamboo",
        "Battersea",
        "Beecher Town",
        "Bensonton",
        "Beverly",
        "Blackstonedge",
        "Borobridge",
        "Browns Town",
        "Calderwood",
        "Cardiiff Hall",
        "Cascade District",
        "Cave Valley",
        "Charles Town",
        "Chester",
        "Claremont",
        "Clydesdale",
        "Colegate",
        "Concord",
        "Coolshade",
        "Davis Town",
        "Discovery Bay",
        "Drax Hall",
        "Epworth",
        "Exchange",
        "Free Hill District",
        "Gibraltar",
        "Glasgow",
        "Hermitage",
        "Hessen Castle",
        "Higgin Town",
        "Hopewell",
        "Keith",
        "Laughlands",
        "Lime Hall",
        "Lime Tree Garden",
        "Linton Park",
        "Llandovery",
        "Lodge",
        "Lydford",
        "Maamee Bay",
        "Madras",
        "Mansfield Meadows",
        "McNie",
        "Moneague",
        "Moriah",
        "Mount Moriah",
        "Mount Pleasant",
        "Mount Zion",
        "Nine Miles District",
        "Ocho Rios",
        "Oracabessa",
        "Orange Hill",
        "Port Maria",
        "Priory",
        "Prospect",
        "Richmond",
        "Rio Neuvo",
        "Runaway Bay",
        "Seville Heights",
        "Shelly Piece",
        "St. Ann",
        "St. Anns Bay",
        "St. Dacres",
        "Steer Town",
        "Sturge Town",
        "Tanglewood",
        "Three Hills",
        "Tolgate",
        "Tower Isle",
        "Village Green",
        "Walkerswood",
        "Watt Town",
        "Wild Cane",
        "York Castle",

        )


        //return montegoBayList.sorted().toSet()

    when(parish.lowercase()) {
        "st james" -> {return montegoBayList.toSet()}
        //"st catherine" -> {return stCatherineList.toSet()}
        "clarendon" -> {return clarendonList.toSet()}
        "trelawny" -> {return trelawnyList.toSet()}
        "st ann" -> {return stAnnList.toSet()}
    }


   return setOf()
}