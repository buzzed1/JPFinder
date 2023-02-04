package com.buzzed.jpfinder.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buzzed.jpfinder.JPFinderTopBar
import com.buzzed.jpfinder.R
import com.buzzed.jpfinder.data.JP
import com.buzzed.jpfinder.data.JPRepository
import com.buzzed.jpfinder.data.OfflineJPRepository
import com.buzzed.jpfinder.navigation.NavigationDestination
import com.buzzed.jpfinder.ui.theme.JPFinderTheme
import kotlinx.coroutines.flow.StateFlow

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
    modifier: Modifier = Modifier
){
    val jp1 = JP(0, "Jones","George","","","","Westgreen","")
    val jp2 = JP(1,"Smith", "Stephen", "George","Someplace","Sometown","Westgreen","smitstephen@someemail.com")


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
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.elevatedCardColors(MaterialTheme.colorScheme.surface)) {
                Column() {

                    Text("ID: ${DetailsScreenDestination.jpId}")
                    if(id == jp1.id) {
                        Text("Name: ${jp1.firstName}  ${jp1.lastName}")
                    }else if (id == jp2.id){
                        Text("Name: ${jp2.firstName}  ${jp2.lastName}")

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
        DetailsScreen({ } ,1)
    }
}