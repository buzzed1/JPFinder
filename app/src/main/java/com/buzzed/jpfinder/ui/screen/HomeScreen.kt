package com.buzzed.jpfinder.ui.screen

import android.app.PendingIntent.getActivity
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
import com.buzzed.jpfinder.JPFinderApplication
import com.buzzed.jpfinder.R
import com.buzzed.jpfinder.data.JPRepository


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
    navigateToListScreen: () -> Unit,
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
        HomeScreenDetails(parishTextValue, communityTextValue,viewModel, {navigateToListScreen()})

    }
}



@Composable
fun HomeScreenDetails(
    parishTextValue: String,
    communityTextValue: String,
    viewModel: HomeViewModel,
    navigateToList: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier.padding(top = 50.dp, bottom = 50.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

        var expanded by remember { mutableStateOf(false)}
        val parishList1 = listOf<Int>(R.string.glendevon, R.string.norwood, R.string.paridise)

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
                    text = "Please Enter Your Parish"
                )
                LocationListDropdown(viewModel.parishList, parishTextValue, icon, viewModel)
                Divider()
                LocationListDropdown(viewModel.communityList, communityTextValue, icon, viewModel)

                Button(
                    modifier = Modifier,
                    onClick = {
                        navigateToList()
                        viewModel.getListOfJP(viewModel.selectedCommunity)
                              },
                    enabled = viewModel.enabledButton
                ) {
                    Text(
                        text = "Find JPs",
                        fontWeight = FontWeight.Bold
                    )
                }
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
                value = viewModel.selectedParish,
                onValueChange = { viewModel.selectParish(it)},
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
                                  },
                        enabled = true
                    )

                }


            }
        } else {
            OutlinedTextField(
                value = viewModel.selectedCommunity,
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
                enabled = viewModel.enabledCommunity

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
                            expanded = false
                                  },
                        enabled = viewModel.enabledCommunity
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
    JPFinderTheme {
        HomeScreenDetails(parishText, communityText, viewModel, {})
    }
}