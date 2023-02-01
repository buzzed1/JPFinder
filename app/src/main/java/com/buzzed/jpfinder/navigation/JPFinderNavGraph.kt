package com.buzzed.jpfinder.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.buzzed.jpfinder.JPFinderApplication
import com.buzzed.jpfinder.ui.screen.*

@Composable
fun JPFinderNavHost(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.factory)
) {
   NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    )
    {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navController,
                modifier = modifier, onNavigateToList = {
                    navController.navigate(route = ListScreenDestination.route)
            })
        }
        composable(
            route = ListScreenDestination.route
        ) {
            val context = LocalContext.current
            Log.d("Arg", ListScreenDestination.route)
            ListScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp()},
                community = viewModel.getCommunity()
            )
        }


    }
}