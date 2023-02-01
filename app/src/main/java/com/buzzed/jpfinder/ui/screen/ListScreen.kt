package com.buzzed.jpfinder.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.buzzed.jpfinder.R
import com.buzzed.jpfinder.data.*
import com.buzzed.jpfinder.navigation.NavigationDestination
import com.buzzed.jpfinder.ui.theme.JPFinderTheme


object ListScreenDestination: NavigationDestination {
    override val route = "list_screen"
    override val titleRes = R.string.list_screen_title
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    community: String,
    modifier: Modifier = Modifier,
    viewModel: ListScreenViewModel = viewModel(factory = AppViewModelProvider.factory),
    homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.factory)

) {
    val communityName = homeViewModel.getCommunity()
    val itemList = viewModel.filterJpInCommunity(communityName)
    val itemList1 = listOf<String>("laptop", "coffee", "soup","Happy", "Sad", "Scared","Super", "Confident")
    Scaffold(
        topBar = {}

    ) {contentPadding ->

        LazyColumn(
            modifier = modifier.padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            items(itemList) {item ->
                 ListResults(item.lastName)

            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListResults(
    results: String?,
    modifier: Modifier = Modifier
) {

    Column(
        //horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(16.dp)
    ) {
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable { }
                    .height(16.dp),
                elevation = CardDefaults.cardElevation(4.dp),
            ) {

                Row(
                    modifier,
                ) {
                    if (results != null) {
                        Text(text = results)
                    } else {
                        Text(text = "No JPs To Show")
                    }

                }

            }

        }

}

@Composable
fun ListScreenTopBar(
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .background(color = MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(R.string.list_screen_title), style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground

        )
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    JPFinderTheme {
        ListScreen(navigateBack = {}, onNavigateUp = {},"")
    }
}