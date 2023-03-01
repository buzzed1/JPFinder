package com.buzzed.jpfinder.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.buzzed.jpfinder.JPFinderTopBar
import com.buzzed.jpfinder.R
import com.buzzed.jpfinder.data.*
import com.buzzed.jpfinder.navigation.NavigationDestination
import com.buzzed.jpfinder.ui.theme.JPFinderTheme


object ListScreenDestination: NavigationDestination {
    override val route = "list_screen"
    override val titleRes = R.string.list_screen_title
    var communityArg = ""
    val routeWithArgs = "${route}?community=${{communityArg}}"
    val arguments = listOf(
        navArgument(communityArg) {
            type = NavType.StringType
            defaultValue = "No JPs Found"
        }
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    communityName: String?,
    onNavigateBack: () -> Unit,
    onDetailsClick: (Int) -> Unit,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    listViewModel: ListScreenViewModel = viewModel(factory = AppViewModelProvider.factory),


) {

    val listUiState by listViewModel.listUiState.collectAsState()
    val itemList = listViewModel.filterJpInCommunity(communityName)
    val context = LocalContext.current

    Scaffold(
        topBar = {
            JPFinderTopBar(
                title = "${stringResource( R.string.list_screen_title)}" +  " in $communityName" ,//ListScreenDestination.titleRes,
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateBack,
                modifier = modifier
            )
        }


    ) { contentPadding ->

        LazyColumn(
                modifier = modifier.padding(contentPadding),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                items(itemList) { item ->
                    if(itemList.isEmpty()){
                        EmptyListResults()
                    } else {
                        if (item.community?.lowercase() == communityName?.lowercase()) {
                            ListResults(item, onDetailsClick)
                        }
                    }
                }


            }


    }

}




@Composable
fun ListResults(
    results: JP?,
    detailsClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier.padding(top = 15.dp, bottom = 15.dp)
    ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .clickable {
                        if (results != null) {
                            DetailsScreenDestination.jpId = results.id!!
                            detailsClick(DetailsScreenDestination.jpId)
                        }

                    }
                    .height(50.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.elevatedCardColors(MaterialTheme.colorScheme.primary)
            ) {


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${results?.lastName}, ${results?.firstName}",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = 20.sp,

                            )
                    }
                    Divider(modifier = modifier.padding(16.dp))

            }



    }

}

@Composable
fun EmptyListResults(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier.padding(top = 15.dp, bottom = 15.dp)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .height(50.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.elevatedCardColors(MaterialTheme.colorScheme.primary)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "No JPs Found",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 20.sp,

                    )
            }

        }



    }

}

//@Composable
//fun NoJPFound(
//    modifier: Modifier = Modifier
//) {
//    Column(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//
//    ) {
//        Text(
//            text = "No JPs Found",
//            fontSize = 24.sp,
//
//            )
//    }
//}

@Preview(showBackground = true,
showSystemUi = true)
@Composable
fun ListScreenPreview() {
    JPFinderTheme {
        ListScreen("",onNavigateBack = {},{},true)
    }
}