package com.buzzed.jpfinder.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.buzzed.jpfinder.R
import com.buzzed.jpfinder.navigation.NavigationDestination

object HomeAndListDestination : NavigationDestination {
    override val route = "homeandlist"
    override val titleRes = R.string.app_name

}

@Composable
fun HomeAndListScreen(
    communityName: String,
    onNavigateToList: ()-> Unit,
    findJPClick: ()-> Unit,
    onNavigateBack: ()-> Unit,
    onDetailsClick: (Int)-> Unit,
    windowSize: WindowWidthSizeClass,
    listViewModel: ListScreenViewModel = viewModel(factory = AppViewModelProvider.factory),
    homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.factory)

) {
    val homeUiState by homeViewModel.uiState.collectAsState()

    if(windowSize == WindowWidthSizeClass.Medium || windowSize == WindowWidthSizeClass.Expanded) {
        Row(
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp, top = 20.dp)
            ) {

                HomeScreen(onNavigateToList = {}, true)
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp, top = 20.dp)
            ) {
                ListScreen(
                    communityName = homeUiState.selectedCommunity,
                    onNavigateBack = {},
                    onDetailsClick = onDetailsClick,
                    canNavigateBack = false
                )
            }

        }
    } else {
        HomeScreen(onNavigateToList = onNavigateToList, false)
    }

}