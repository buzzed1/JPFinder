package com.buzzed.jpfinder.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.buzzed.jpfinder.ui.screen.*
import com.buzzed.jpfinder.ui.screen.ListScreenDestination.communityArg

@Composable
fun JPFinderNavHost(
    navController: NavHostController,
    modifier: Modifier,
    windowSize: WindowWidthSizeClass

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
                HomeScreen(
                    onNavigateToList = {
                        navController.navigate(ListScreenDestination.routeWithArgs)
                    },
                    onDetailsClick = { },
                    isLargeSize = false
                )
            }
        composable(
            route = ListScreenDestination.routeWithArgs,
            arguments = ListScreenDestination.arguments,
            //deepLinks = listOf(navDeepLink {uriPattern= "uri=android-app://androidx.navigation/list_screen?communityArg={${communityArg}}"})
        ){
            ListScreen(
                communityName = communityArg,
                onDetailsClick = {navController.navigate(DetailsScreenDestination.route)},
                onNavigateBack = { navController.popBackStack() },
                canNavigateBack = true
            )
        }

        composable(
            route = DetailsScreenDestination.route,
            //arguments = ListScreenDestination.arguments,
            //deepLinks = listOf(navDeepLink {uriPattern= "uri=android-app://androidx.navigation/list_screen?communityArg={${communityArg}}"})
        ){

            DetailsScreen(
                onNavigateUp = { navController.popBackStack() },
                id = DetailsScreenDestination.jpId,
                isLargeSize = false,
                canNavigateBack = true
            )
        }




    }
}