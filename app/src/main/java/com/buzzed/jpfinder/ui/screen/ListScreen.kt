package com.buzzed.jpfinder.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buzzed.jpfinder.R
import com.buzzed.jpfinder.data.*
import com.buzzed.jpfinder.navigation.NavigationDestination
import com.buzzed.jpfinder.ui.theme.JPFinderTheme


object ListScreenDestination: NavigationDestination {
    override val route = "list_screen"
    override val titleRes = R.string.list_screen_title
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val itemList = listOf<String>("laptop", "coffee", "soup","Happy", "Sad", "Scared","Super", "Confident")
    Scaffold(
        topBar = {}

    ) {contentPadding ->
        val viewModel =
        LazyColumn(
            modifier = modifier.padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            items(itemList) {item ->
                 ListResults(item)

            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListResults(
    results: String,
    modifier: Modifier = Modifier
) {
    var listItems: List<String> = listOf()
    Column(
        //horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(16.dp)
    ) {
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable {  }
                    .height(16.dp),
                elevation = CardDefaults.cardElevation(4.dp),
            ) {

                Row(
                    modifier,
                ) {
                    Text(text = results)

                }

            }

        }

}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    JPFinderTheme {
        ListScreen(navigateBack = {}, onNavigateUp = {})
    }
}