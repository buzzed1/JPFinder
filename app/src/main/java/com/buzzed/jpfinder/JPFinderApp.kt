package com.buzzed.jpfinder

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.buzzed.jpfinder.navigation.JPFinderNavHost
import com.buzzed.jpfinder.ui.screen.*
import com.buzzed.jpfinder.ui.theme.JPFinderTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JPFinderApp(
    navController: NavHostController = rememberNavController(),
    windowSize: WindowWidthSizeClass,
) {

    JPFinderNavHost(
        navController = navController,
        modifier = Modifier,
        windowSize = windowSize
    )

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JPFinderTopBar(
    title: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit = {},
    modifier: Modifier = Modifier
) {


    if(canNavigateBack){
        CenterAlignedTopAppBar(
            title = {
                Text(
                    title,
                    color = MaterialTheme.colorScheme.onBackground
                )
                    },
            modifier = Modifier,
            navigationIcon = {
                IconButton(
                    onClick = navigateUp ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface,
                titleContentColor = MaterialTheme.colorScheme.onSurface
            )
        )


    } else {
        CenterAlignedTopAppBar (
            title = {
                Text(
                    title,
                    color = MaterialTheme.colorScheme.onBackground
                )
                    },
            modifier = modifier,
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface,
                titleContentColor = MaterialTheme.colorScheme.onSurface
            )
        )

        }
    }



@Preview
@Composable
fun JPFinderThemePreview() {
    JPFinderTheme(useDarkTheme = false) {
        JPFinderTopBar(stringResource( R.string.app_name),false, {})
    }
}

@Preview
@Composable
fun JPFinderDarkThemePreview() {
    JPFinderTheme(useDarkTheme = true) {
        JPFinderTopBar(stringResource( R.string.app_name),true, {})
    }
}