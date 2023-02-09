package com.buzzed.jpfinder.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.buzzed.jpfinder.JPFinderTopBar
import com.buzzed.jpfinder.R
import com.buzzed.jpfinder.navigation.NavigationDestination
import com.buzzed.jpfinder.ui.theme.JPFinderTheme

object DetailsScreenDestination: NavigationDestination {
    override val route = "details_screen"
    override val titleRes = R.string.jp_details
    var jpId = 0

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    onNavigateUp: () -> Unit,
    id: Int,
    modifier: Modifier = Modifier,
    viewModel: DetailsScreenViewModel = viewModel(factory = AppViewModelProvider.factory),

    ) {
    val jp = viewModel.getFilteredJP()




    Scaffold(
        topBar = {
            JPFinderTopBar(
                title = DetailsScreenDestination.titleRes,
                navigateUp = onNavigateUp,
                canNavigateBack = true,
            )
        }
    ) {

        Column(modifier = modifier.padding(it)) {


            Column(
                modifier = modifier.padding(top = 20.dp),

                ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.elevatedCardColors(MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.padding(all = 16.dp)
                        //verticalArrangement = Arrangement.Center
                    ) {

                        Column(modifier = modifier.padding(paddingValues = it)) {

                            Text(
                                "FirstName:",
                                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                            )

                            Text("${jp?.firstName}")

                        }
                        Divider()
                        Column() {
                            Text(
                                "MiddleName: ",
                                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                            )
                            Text("${jp?.middleName}")
                        }
                        Divider()
                        Column() {
                            Text(
                                "LastName: ",
                                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                            )
                            Text("${jp?.lastName}")
                        }
                        Divider()
                        Column() {
                            Text(
                                "Address1: ",
                                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                            )
                            Text("${jp?.address1}")

                        }
                        Divider()
                        Column() {
                            Text(
                                "Address2: ",
                                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                            )
                            Text("${jp?.address2}")
                        }
                        Divider()
                        Column() {
                            Text(
                                "Community: ",
                                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                            )
                            Text("${jp?.community}")
                        }
                        Divider()
                        Column() {
                            Text(
                                "Email Address: ",
                                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                            )
                            Text("${jp?.emailAddress}",
                                modifier = Modifier.clickable { })
                        }
                        Divider()
                    }
                }
            }
        }

    }
}






@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun DetailsScreenPreview() {
    JPFinderTheme {

        //DetailsScreen({ } ,0, modifier = Modifier, jpDao)
    }
}

@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun DetailsScreenPreview1() {
    JPFinderTheme {
        //DetailsScreen({ } ,1)
    }
}