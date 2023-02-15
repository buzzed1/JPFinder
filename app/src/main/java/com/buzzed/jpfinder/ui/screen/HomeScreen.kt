package com.buzzed.jpfinder.ui.screen



import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.buzzed.jpfinder.JPFinderTopBar
import com.buzzed.jpfinder.R
import com.buzzed.jpfinder.data.parishList
import com.buzzed.jpfinder.data.towns
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
) {
Scaffold(
    topBar = {
        JPFinderTopBar(
            title = R.string.app_name, //HomeDestination.titleRes,
            canNavigateBack = false,
    )
    }
) {
    Column(modifier = modifier.padding(it)) {
        HomeScreenBody(onNavigateToList = onNavigateToList)
    }

}

}

@Composable
fun HomeScreenBody(
    onNavigateToList: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.factory)
) {
    val homeUiState by viewModel.uiState.collectAsState()
    //val listUiState by listviewModel.uiState.collectAsState()
    val parishList = parishList()
    val communityList = towns(homeUiState.selectedParish)
    viewModel.updateLists(parishList, communityList)
    val context = LocalContext.current
    val parishLabel = "Select Parish"
    val communityLabel = "Select Community"


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)

    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.secondary
            ),
            modifier = modifier.height(400.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {


                Text(
                    text = "Please Select Options Below",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.headlineSmall
                )

                Divider()

                DropDownMenuParish(
                    parishList,
                    homeUiState,
                    viewModel,
                    context,
                    parishLabel,
                )
                Spacer(modifier = Modifier.width(10.dp))
                DropDownMenuCommunity(
                    communityList.toList(),
                    homeUiState,
                    viewModel,
                    context,
                    communityLabel,
                )

                Divider()
                Button(
                    modifier = Modifier,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    onClick = {
                        ListScreenDestination.communityArg = homeUiState.selectedCommunity
                        onNavigateToList()

                    },
                    enabled = homeUiState.enabledButton,

                    ) {
                    Text(
                        text = "Find JPs",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }

    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenuParish(
    options: List<Int>,
    uiState: HomeUiState,
    viewModel: HomeViewModel,
    context: Context,
    label: String,
    modifier: Modifier = Modifier

) {

    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier.fillMaxWidth()
    ) {

        TextField(
            readOnly = true,
            value = uiState.selectedParish,
            onValueChange = { uiState.selectedParish },
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded =  expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded =  expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    { Text(stringResource(selectionOption)) },
                    onClick = {
                        viewModel.selectParish(context.getString(selectionOption))
                        selectedOptionText = context.getString(selectionOption)
                        expanded = false
                        //viewModel.enableCommunity()
                    },
                    modifier = Modifier.fillMaxWidth()

                )
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenuCommunity(
    options: List<Int>,
    uiState: HomeUiState,
    viewModel: HomeViewModel,
    context: Context,
    label: String,
    modifier: Modifier = Modifier

) {
    //val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier.fillMaxWidth()
    ) {

        TextField(
            readOnly = true,
            value = uiState.selectedCommunity,
            onValueChange = { uiState.selectedCommunity },
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded =  expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded =  expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    { Text(stringResource(selectionOption)) },
                    onClick = {
                        viewModel.selectCommunity(context.getString(selectionOption))
                        selectedOptionText = context.getString(selectionOption)
                        expanded = false
                        viewModel.enableButton()
                    },
                    modifier = Modifier.fillMaxWidth()

                    )
            }
        }

    }
}


@Composable
fun favoriteList(){
    LazyColumn( ) {

    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {

    JPFinderTheme {
        HomeScreen( { } )
    }
}