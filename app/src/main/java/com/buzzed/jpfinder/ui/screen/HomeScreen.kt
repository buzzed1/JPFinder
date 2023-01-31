package com.buzzed.jpfinder.ui.screen

import android.app.PendingIntent.getActivity
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.buzzed.jpfinder.JPFinderApplication
import com.buzzed.jpfinder.R
import com.buzzed.jpfinder.data.JPRepository
import com.buzzed.jpfinder.data.ParishList
import com.buzzed.jpfinder.data.Towns


import com.buzzed.jpfinder.navigation.NavigationDestination
import com.buzzed.jpfinder.ui.theme.JPFinderTheme
import kotlinx.coroutines.selects.select


object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name

}



//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.factory)
) {
    var parishTextValue by rememberSaveable { mutableStateOf("Parish") }
    var communityTextValue by rememberSaveable { mutableStateOf("Community") }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        HomeScreenDetails(parishTextValue, communityTextValue,navController, viewModel)

    }
}



@Composable
fun HomeScreenDetails(
    parishTextValue: String,
    communityTextValue: String,
    navController: NavController,
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier.padding(top = 50.dp, bottom = 50.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
    ) {

        var expanded by remember { mutableStateOf(false)}
        val parishList = ParishList()
        val communityList = Towns()
        viewModel.updateLists(parishList, communityList)

        val icon = if (expanded)
            Icons.Filled.ArrowForward //it requires androidx.compose.material:material-icons-extended
        else
            Icons.Filled.ArrowDropDown

        Column(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

                Text(
                    text = "Please Enter Your Parish",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.headlineMedium
                )
                LocationListDropdown(parishList, parishTextValue, icon, viewModel)
                Divider()
                LocationListDropdown(communityList, communityTextValue, icon, viewModel)

                Button(
                    modifier = Modifier,
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    onClick = {
                        navController.navigate(ListScreenDestination.route)
                              },
                    enabled = true
                ) {
                    Text(
                        text = "Find JPs",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            Text(text = "Parish: ${viewModel.getParish()}")
            Text(text = "Community: ${viewModel.getCommunity()}")
            Text(text = "Community enabled: ${viewModel.getEnabledCommunity()} ")
            Text(text = "Button Enabled: ${viewModel.getEnabledButton()}")
            }
        }
    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationListDropdown(
    listItems: List<Int> ,
    locationText: String,
    icon: ImageVector,
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("")}
    var textfieldSize by remember { mutableStateOf(Size.Zero)}
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
        ) {
        if (locationText == "Parish") {
            OutlinedTextField(
                value = viewModel.getParish(),
                onValueChange = { viewModel.selectParish(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        textfieldSize = coordinates.size.toSize()
                    },
                label = { Text(text = "Select $locationText") },
                trailingIcon = {
                    Icon(icon, contentDescription = "Select Parish",
                        modifier = Modifier.clickable { expanded = !expanded }
                    )

                },
                enabled = true,
                textStyle = MaterialTheme.typography.titleSmall

            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
            ) {
                listItems.forEach { label ->
                    DropdownMenuItem(
                        text = { Text(text = stringResource(label)) },
                        onClick = {
                            viewModel.selectParish(context.getString(label))
                            expanded = false
                            viewModel.enableCommunity()
                                  },
                        enabled = true,

                    )

                }


            }
        } else {
            OutlinedTextField(
                value = viewModel.getCommunity(),
                onValueChange = { viewModel.selectCommunity(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        textfieldSize = coordinates.size.toSize()
                    },
                label = { Text(text = "Select $locationText") },
                trailingIcon = {
                    Icon(icon, contentDescription = "Select Community",
                        modifier = Modifier.clickable { expanded = !expanded }
                    )
                },
                enabled = true

            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
            ) {
                listItems.forEach { label ->
                    DropdownMenuItem(
                        text = { Text(text = stringResource(label)) },
                        onClick = {
                            viewModel.selectCommunity(context.getString(label))
                            viewModel.enableButton()
                            expanded = false
                                  },
                        enabled = viewModel.getEnabledCommunity()
                    )

                }


            }

        }
    }

}










@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val parishText = "Parish"
    val communityText = "Community"
    val viewModel = HomeViewModel(JPFinderApplication().container.jpRepository)
    val navController = rememberNavController()
    JPFinderTheme {
        HomeScreenDetails(parishText, communityText, navController, viewModel)
    }
}