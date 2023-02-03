package com.buzzed.jpfinder.ui.screen


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.buzzed.jpfinder.JPFinderApplication
import com.buzzed.jpfinder.JPFinderTopBar
import com.buzzed.jpfinder.R
import com.buzzed.jpfinder.data.ParishList
import com.buzzed.jpfinder.data.Towns
import com.buzzed.jpfinder.navigation.NavigationDestination
import com.buzzed.jpfinder.ui.theme.JPFinderTheme


object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToList: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
) {
    val screenName = R.string.app_name
Scaffold(
    topBar = {
        JPFinderTopBar(
            title = R.string.app_name, //HomeDestination.titleRes,
            canNavigateBack = false,
    )
    }
) {
    Column(modifier = modifier.padding(it)) {
        HomeScreenBody(onNavigateToList = onNavigateToList, navController = navController)
    }

}

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenBody(
    navController: NavController,
    onNavigateToList: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.factory),
    listviewModel: ListScreenViewModel = viewModel(factory = AppViewModelProvider.factory)
) {
    val homeUiState by viewModel.uiState.collectAsState()
    val listUiState by listviewModel.uiState.collectAsState()
    var expandedParishList by remember { mutableStateOf(false)}
    var expandedCommunityList by remember { mutableStateOf(false)}
    val parishList = ParishList()
    val communityList = Towns()
    viewModel.updateLists(parishList, communityList)
    var textfieldSize by remember { mutableStateOf(Size.Zero)}
    val context = LocalContext.current



    val parishIcon = if (expandedParishList)
        Icons.Filled.ArrowForward //it requires androidx.compose.material:material-icons-extended
    else
        Icons.Filled.ArrowDropDown

    val communityIcon = if (expandedCommunityList)
        Icons.Filled.ArrowForward //it requires androidx.compose.material:material-icons-extended
    else
        Icons.Filled.ArrowDropDown


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)

        ) {
        Card(modifier = modifier.height(400.dp)) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {


                Text(
                    text = "Please Enter Your Parish",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.headlineMedium
                )

                Divider()
                OutlinedTextField(
                    value = homeUiState.selectedParish,
                    onValueChange = { homeUiState.selectedParish },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            textfieldSize = coordinates.size.toSize()
                        },
                    label = { Text(text = "Select Parish") },
                    trailingIcon = {
                        Icon(parishIcon, contentDescription = "Select Parish",
                            modifier = Modifier.clickable {
                                expandedParishList = !expandedParishList
                            }
                        )

                    },
                    enabled = true,
                    textStyle = MaterialTheme.typography.titleSmall

                )
                DropdownMenu(
                    expanded = expandedParishList,
                    onDismissRequest = { expandedParishList = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
                ) {
                    parishList.forEach { label ->
                        DropdownMenuItem(
                            text = { Text(text = stringResource(label)) },
                            onClick = {

                                viewModel.selectParish(context.getString(label))
                                expandedParishList = false
                                viewModel.enableCommunity()
                            },
                            enabled = true,

                            )

                    }


                }


                Spacer(modifier = Modifier.width(10.dp))

                OutlinedTextField(
                    value = homeUiState.selectedCommunity,
                    onValueChange = { viewModel.selectCommunity(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            textfieldSize = coordinates.size.toSize()
                        },
                    label = { Text(text = "Select Community") },
                    trailingIcon = {
                        Icon(communityIcon, contentDescription = "Select Community",
                            modifier = Modifier.clickable {
                                expandedCommunityList = !expandedCommunityList
                            }
                        )
                    },
                    enabled = homeUiState.enabledCommunity

                )
                DropdownMenu(
                    expanded = expandedCommunityList,
                    onDismissRequest = { expandedCommunityList = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
                ) {
                    communityList.forEach { label ->
                        DropdownMenuItem(
                            text = { Text(text = stringResource(label)) },
                            onClick = {
                                viewModel.selectCommunity(context.getString(label))
                                viewModel.enableButton()
                                expandedCommunityList = false
                            },
                            enabled = homeUiState.enabledCommunity
                        )

                    }


                }

                Divider()
                Button(
                    modifier = Modifier,
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    onClick = {
                        ListScreenDestination.communityArg = homeUiState.selectedCommunity
                        onNavigateToList()

                    }



                    ,
                    enabled = homeUiState.enabledButton
                ) {
                    Text(
                        text = "Find JPs",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }

    Card(modifier = Modifier
        .height(150.dp)
        .fillMaxWidth()) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(16.dp)

    ) {


        Text(text = "Parish: ${viewModel.getParish()}")
        Text(text = "Community: ${viewModel.getCommunity()}")
        Text(text = "Community enabled: ${viewModel.getEnabledCommunity()} ")
        Text(text = "Button Enabled: ${viewModel.getEnabledButton()}")
    }
    }
    }
}















@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {

    JPFinderTheme {
        HomeScreen( { } )
    }
}