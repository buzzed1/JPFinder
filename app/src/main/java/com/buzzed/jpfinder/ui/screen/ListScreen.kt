package com.buzzed.jpfinder.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.buzzed.jpfinder.JPFinderTopBar
import com.buzzed.jpfinder.R
import com.buzzed.jpfinder.data.*
import com.buzzed.jpfinder.navigation.NavigationDestination
import com.buzzed.jpfinder.ui.screen.AppViewModelProvider.factory
import com.buzzed.jpfinder.ui.screen.ListScreenDestination.titleRes
import com.buzzed.jpfinder.ui.theme.JPFinderTheme


object ListScreenDestination: NavigationDestination {
    override val route = "list_screen"
    override val titleRes = R.string.list_screen_title
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    viewModel: ListScreenViewModel = viewModel(factory = AppViewModelProvider.factory),
    homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.factory)

) {
    val uiState by homeViewModel.uiState.collectAsState()
    val communityName = uiState.selectedCommunity
    val itemList = viewModel.filterJpInCommunity(communityName) //listOf<String>("Super Man", "Super Duper", "John Jones")//
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            JPFinderTopBar(
                title = ListScreenDestination.titleRes,
                canNavigateBack = true,
                navigateUp = onNavigateBack,
                modifier = modifier
            )
        },
        containerColor = MaterialTheme.colorScheme.background

    ) {contentPadding ->

        LazyColumn(
            modifier = modifier.padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            items(itemList) {item ->
                 ListResults(item, communityName)

            }

        }
    }
}



@Composable
fun ListResults(
    results: JP,
    communityName: String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier.padding(top = 15.dp)
    ) {
        //Divider( modifier = modifier.padding(16.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .clickable { }
                    .height(50.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.elevatedCardColors(MaterialTheme.colorScheme.surface)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (results.community != "" ) {
                        Text(
                            text = "${results.lastName}, ${results.firstName}",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 20.sp,

                        )
                    } else {
                        Text(text = "No JPs To Show")
                    }

                }

            }
        Divider(modifier = modifier.padding(16.dp))

        }

}



@Preview(showBackground = true,
showSystemUi = true)
@Composable
fun ListScreenPreview() {
    JPFinderTheme {
        ListScreen(onNavigateBack = {})
    }
}