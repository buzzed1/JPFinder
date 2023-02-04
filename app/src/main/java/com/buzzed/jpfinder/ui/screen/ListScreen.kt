package com.buzzed.jpfinder.ui.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.buzzed.jpfinder.JPFinderTopBar
import com.buzzed.jpfinder.R
import com.buzzed.jpfinder.data.*
import com.buzzed.jpfinder.navigation.NavigationDestination
import com.buzzed.jpfinder.ui.screen.AppViewModelProvider.factory
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
    navController: NavController? = rememberNavController(),
    listViewModel: ListScreenViewModel = viewModel(factory = AppViewModelProvider.factory),


) {

    val listUiState by listViewModel.uiState.collectAsState()
    val jp = JP(0, "Jones","George","","","","Westgreen","")
    val jp2 = JP(1,"Smith", "Stephen", "George","Someplace","Sometown","Westgreen","smitstephen@someemail.com")
    var itemList = mutableListOf<JP>()   //listOf<String>("Super Man", "Super Duper", "John Jones")//
    itemList.add(jp)
    itemList.add(jp2)
    Log.d("JP", "${itemList.toString()}")
    val context = LocalContext.current
    Toast.makeText(context,"Community Name = $communityName", Toast.LENGTH_LONG).show()
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
        /*Column(modifier = modifier.padding(contentPadding)) {
            if(communityName == null) {
                Text(text = "Nothing Came")

            }else {
                Text(text = communityName)
            }*/

        LazyColumn(
                modifier = modifier.padding(contentPadding),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                items(itemList) { item ->
                    ListResults(item, communityName,onDetailsClick)

                }

            }
        }
    }




@Composable
fun ListResults(
    results: JP,
    communityName: String?,
    detailsClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier.padding(top = 15.dp)
    ) {
        //Divider( modifier = modifier.padding(16.dp))
        /*Text(
            text = "${results.lastName}, ${results.firstName}",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 20.sp,

            )*/
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .clickable {
                        DetailsScreenDestination.jpId = results.id
                        detailsClick(DetailsScreenDestination.jpId) }
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
                            text = "${results.lastName}, ${results.firstName}",
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