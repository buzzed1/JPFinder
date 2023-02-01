package com.buzzed.jpfinder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.buzzed.jpfinder.navigation.JPFinderNavHost
import com.buzzed.jpfinder.ui.screen.HomeScreen
import com.buzzed.jpfinder.ui.screen.HomeViewModel
import com.buzzed.jpfinder.ui.screen.ListScreenDestination
import com.buzzed.jpfinder.ui.theme.JPFinderTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JPFinderApp(
navController: NavHostController = rememberNavController(),
){
    JPFinderNavHost(navController = navController,
    modifier = Modifier
        )
    Scaffold(
        topBar = { JPFinderTopBar() }
    ) {
        Column(modifier = Modifier.padding(it)) {
            HomeScreen( navController, {}, modifier = Modifier, )

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JPFinderTopBar(
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .background(color = MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(R.string.app_name), style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground

            )
    }
}

@Preview
@Composable
fun JPFinderThemePreview() {
    JPFinderTheme(darkTheme = false) {
        JPFinderTopBar()
    }
}

@Preview
@Composable
fun JPFinderDarkThemePreview() {
    JPFinderTheme(darkTheme = true) {
        JPFinderTopBar()
    }
}