package com.buzzed.jpfinder

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.buzzed.jpfinder.ui.screen.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JPFinderApp(
    modifier: Modifier = Modifier
) {
    Scaffold (
        topBar = {}
            ) { paddingValues ->
        Column(modifier = modifier.padding(paddingValues)) {
            HomeScreen()
        }

    }
}