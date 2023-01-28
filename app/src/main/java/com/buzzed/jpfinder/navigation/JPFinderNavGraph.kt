package com.buzzed.jpfinder.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.buzzed.jpfinder.ui.screen.HomeDestination
import com.buzzed.jpfinder.ui.screen.ListScreen

@Composable
fun JPFinderNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
   NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    )
    {
        composable(route = HomeDestination.route) {
            ListScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp()},
            )
        }

    }
}