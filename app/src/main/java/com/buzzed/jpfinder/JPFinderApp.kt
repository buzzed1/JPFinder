package com.buzzed.jpfinder

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.buzzed.jpfinder.navigation.JPFinderNavHost
import com.buzzed.jpfinder.ui.screen.HomeScreen
import com.buzzed.jpfinder.ui.screen.HomeViewModel
import com.buzzed.jpfinder.ui.screen.ListScreenDestination


@Composable
fun JPFinderApp(
navController: NavHostController = rememberNavController()){
    JPFinderNavHost(navController = navController,
    modifier = Modifier
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JPFinderTopBar(
    modifier: Modifier = Modifier
) {
    Scaffold (
        topBar = {}
            ) { paddingValues ->
        Column(modifier = modifier.padding(paddingValues)) {

        }

    }
}