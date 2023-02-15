package com.buzzed.jpfinder.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.buzzed.jpfinder.R
import com.buzzed.jpfinder.navigation.NavigationDestination

object HomeAndListDestination : NavigationDestination {
    override val route = "homeAndList"
    override val titleRes = R.string.app_name

}

@Composable
fun HomeAndListScreen(
    communityName: String,
    onNavigateToList: ()-> Unit,
    onNavigateBack: ()-> Unit,
    onDetailsClick: (Int)-> Unit,


) {
        Row(
            ) {
                Column(modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp, top = 20.dp)) {

                    HomeScreen(onNavigateToList = { } )
                }
                Column(modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp, top = 20.dp)) {
                    ListScreen(
                        communityName = communityName,
                        onNavigateBack = onNavigateBack,
                        onDetailsClick = onDetailsClick
                    )
                }

            }

}