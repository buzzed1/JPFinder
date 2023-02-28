package com.buzzed.jpfinder.ui.screen



import android.content.Context
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.buzzed.jpfinder.JPFinderTopBar
import com.buzzed.jpfinder.R
import com.buzzed.jpfinder.data.parishList
import com.buzzed.jpfinder.data.towns
import com.buzzed.jpfinder.navigation.NavigationDestination
import com.buzzed.jpfinder.ui.theme.JPFinderTheme
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView


object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToList: () -> Unit,
    onDetailsClick: (Int)-> Unit,
    isLargeSize: Boolean,
    modifier: Modifier = Modifier,
) {
Scaffold(
    topBar = {
        JPFinderTopBar(
            title = stringResource( R.string.app_name), //HomeDestination.titleRes,
            canNavigateBack = false,
    )
    }
) {
    Column(modifier = modifier.padding(it)) {
        HomeScreenBody(onNavigateToList = onNavigateToList,isLargeSize, onDetailsClick )
    }

}

}

@Composable
fun HomeScreenBody(
    onNavigateToList: () -> Unit,
    isLargeSize: Boolean,
    onDetailsClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.factory),
    detailsScreenViewModel: DetailsScreenViewModel = viewModel(factory = AppViewModelProvider.factory)
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
                if (isLargeSize){

                }else {
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
               //FavoriteList(viewModel = detailsScreenViewModel, onDetailsClick = onDetailsClick  )
            }

        }

        bannerAds(context = context)
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenuParish(
    options: List<String>,
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
                    { Text(selectionOption) },
                    onClick = {
                        viewModel.selectParish(selectionOption)
                        selectedOptionText = selectionOption
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
    options: List<String>,
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
                    { Text(selectionOption) },
                    onClick = {
                        viewModel.selectCommunity(selectionOption)
                        selectedOptionText = selectionOption
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
fun bannerAds(context: Context) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Spacer(modifier = Modifier.height(200.dp))

        AndroidView(
            factory = { context ->
                AdView(context).apply {
                   setAdSize(AdSize.BANNER)
                    adUnitId = "ca-app-pub-3940256099942544/6300978111"
                    loadAd(AdRequest.Builder().build())
                }
            }
        )

    }
}

@Composable
fun FavoriteList(
    viewModel: DetailsScreenViewModel,
    onDetailsClick: (Int)-> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val list = viewModel.getFavoriteJPs()

        if(list.isEmpty()){
            Text("No Favorites")
        }else {
            for (jp in list) {
                ListResults(jp, onDetailsClick,)
            }
        }
    }


}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {

    JPFinderTheme {
        //HomeScreen( { },false )
    }
}