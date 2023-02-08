package com.buzzed.jpfinder.ui.screen

import android.widget.Toast
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
    var communityArg = "Westgreen"
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
    modifier: Modifier = Modifier,
    listViewModel: ListScreenViewModel = viewModel(factory = AppViewModelProvider.factory),


) {

    val listUiState by listViewModel.listUiState.collectAsState()
    val itemList = listUiState.jpList
    val context = LocalContext.current

    Scaffold(
        topBar = {
            JPFinderTopBar(
                title = R.string.list_screen_title,//ListScreenDestination.titleRes,
                canNavigateBack = true,
                navigateUp = onNavigateBack,
                modifier = modifier
            )
        },
        containerColor = MaterialTheme.colorScheme.surface

    ) { contentPadding ->

        LazyColumn(
                modifier = modifier.padding(contentPadding),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                items(itemList) { item ->
                    if(item.community == communityName) {
                        ListResults(item, onDetailsClick)
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
        modifier = Modifier.padding(top = 15.dp)
    ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .clickable {
                        if (results != null) {
                            DetailsScreenDestination.jpId = results.id!!
                            detailsClick(DetailsScreenDestination.jpId)
                        } else {

                        }

                    }
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
                        Text(
                            text = "${results?.lastName}, ${results?.firstName}",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 20.sp,

                        )
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
        ListScreen("",onNavigateBack = {},{})
    }
}