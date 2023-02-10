package com.buzzed.jpfinder.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.buzzed.jpfinder.JPFinderTopBar
import com.buzzed.jpfinder.R
import com.buzzed.jpfinder.navigation.NavigationDestination
import com.buzzed.jpfinder.ui.theme.JPFinderTheme
import kotlinx.coroutines.delay

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

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(start = 8.dp, end = 8.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.elevatedCardColors(MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.padding(start = 8.dp, bottom = 12.dp)
                        //verticalArrangement = Arrangement.Center
                    ) {

                        Column(modifier = modifier) {

                            Text(
                                "FirstName:",
                                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                                fontSize = 20.sp,

                            )

                            Text("${jp?.firstName}",
                                fontSize = 24.sp
                            )

                        }
                        Divider()
                        Column() {
                            Text(
                                "MiddleName: ",
                                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                                fontSize = 20.sp
                            )
                            Text("${jp?.middleName}",
                                fontSize = 24.sp
                            )
                        }
                        Divider()
                        Column() {
                            Text(
                                "LastName: ",
                                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                                fontSize = 20.sp
                            )
                            Text("${jp?.lastName}",
                                fontSize = 24.sp
                            )
                        }
                        Divider()
                        Column() {
                            Text(
                                "Address1: ",
                                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                                fontSize = 20.sp
                            )
                            Text("${jp?.address1}",
                                fontSize = 24.sp
                            )

                        }
                        Divider()
                        Column() {
                            Text(
                                "Address2: ",
                                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                                fontSize = 20.sp
                            )
                            Text("${jp?.address2}",
                                fontSize = 24.sp
                            )
                        }
                        Divider()
                        Column() {
                            Text(
                                "Community: ",
                                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                                fontSize = 20.sp
                            )
                            Text("${jp?.community}",
                                fontSize = 24.sp
                            )
                        }
                        Divider()
                        Column() {
                            Text(
                                "Email Address: ",
                                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                                fontSize = 20.sp
                            )
                            Text("${jp?.emailAddress}",
                                modifier = Modifier.clickable { },
                                fontSize = 24.sp
                            )
                        }
                        Divider()
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

        DetailsScreen({ } ,0, modifier = Modifier)
    }
}

@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun DetailsScreenPreview1() {
    JPFinderTheme {
        DetailsScreen({ } ,1)
    }
}