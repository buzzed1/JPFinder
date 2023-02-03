package com.buzzed.jpfinder.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.buzzed.jpfinder.ui.screen.*
import com.buzzed.jpfinder.ui.screen.ListScreenDestination.communityArg
import com.buzzed.jpfinder.ui.screen.ListScreenDestination.route

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
            val context = LocalContext.current
            HomeScreen(
                onNavigateToList = {
                    navController.navigate("${ListScreenDestination.route}?community={${communityArg}}")
                })
        }
        composable(
            route = "${ListScreenDestination.route}?community={${communityArg}}",
            arguments = ListScreenDestination.arguments,
            //deepLinks = listOf(navDeepLink {uriPattern= "uri=android-app://androidx.navigation/list_screen?communityArg={${communityArg}}"})
        ){
            Log.d("ListScreen","$communityArg     ${it.arguments?.getString("community")}")
            ListScreen(
                communityName = communityArg,
                onNavigateBack = { navController.popBackStack() })
        }





    }
}