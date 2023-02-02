package com.buzzed.jpfinder.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.buzzed.jpfinder.ui.screen.*

@Composable
fun JPFinderNavHost(
    navController: NavHostController,
    modifier: Modifier,

) {
   NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    )
    {
        composable(
            route = HomeDestination.route
        ) {
            HomeScreen(onNavigateToList = { navController.navigate(ListScreenDestination.route)})
        }
        composable(
            route = ListScreenDestination.route
        ) {
            ListScreen(onNavigateBack = { navController.popBackStack() })
        }





    }
}