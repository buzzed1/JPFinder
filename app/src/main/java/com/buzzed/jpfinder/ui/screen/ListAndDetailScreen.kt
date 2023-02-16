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

object ListAndDetailDestination : NavigationDestination {
    override val route = "listanddetail"
    override val titleRes = R.string.jp_details

}

@Composable
fun ListAndDetailScreen(
    windowSize: WindowWidthSizeClass,
    id: Int,
    onNavigateBack: ()-> Unit,
    isLargeSize: Boolean,
    onDetailsClick: (Int)-> Unit,
    canNavigateBack: Boolean,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.factory)

) {
    val homeUiState by viewModel.uiState.collectAsState()

    if(windowSize == WindowWidthSizeClass.Medium || windowSize == WindowWidthSizeClass.Expanded) {
        Row() {
            Column(modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp, top = 20.dp)
            ) {
                ListScreen(
                    communityName = homeUiState.selectedCommunity,
                    onNavigateBack = onNavigateBack,
                    onDetailsClick = { }  ,
                    canNavigateBack = true
                )
            }
            Column( modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp, top = 20.dp)
            ) {
                DetailsScreen(
                    onNavigateUp = onNavigateBack,
                    id = id,
                    isLargeSize = true,
                    canNavigateBack = canNavigateBack
                )
            }
        }
    } else {

    }
}