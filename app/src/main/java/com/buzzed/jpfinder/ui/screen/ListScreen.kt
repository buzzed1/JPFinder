package com.buzzed.jpfinder.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buzzed.jpfinder.data.*
import com.buzzed.jpfinder.ui.theme.JPFinderTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
) {

    val itemList = listOf<String>("laptop", "coffee", "soup")
    Scaffold(
        topBar = {}

    ) {contentPadding ->

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
    var firstname = results

Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier.padding(16.dp)
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {  },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(5.dp)

    ) {

        Row(
            modifier,
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = firstname)

        }

    }

    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    JPFinderTheme {
        ListScreen()
    }
}