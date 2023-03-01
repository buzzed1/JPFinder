package com.buzzed.jpfinder.ui.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.buzzed.jpfinder.JPFinderTopBar
import com.buzzed.jpfinder.R
import com.buzzed.jpfinder.data.JP
import com.buzzed.jpfinder.navigation.NavigationDestination
import com.buzzed.jpfinder.ui.theme.JPFinderTheme


object DetailsScreenDestination: NavigationDestination {
    override val route = "details_screen"
    override val titleRes = R.string.jp_details
    var jpId = 0

}


@SuppressLint("UnusedTransitionTargetStateParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    onNavigateUp: () -> Unit,
    id: Int,
    isLargeSize: Boolean,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    viewModel: DetailsScreenViewModel = viewModel(factory = AppViewModelProvider.factory),

    ) {
    val context = LocalContext.current
    val addresses = arrayOf("")

    val headLineTextSize: TextUnit

    val infoTextSize: TextUnit

    when {
        isLargeSize -> {
            headLineTextSize  = 15.sp
            infoTextSize  = 17.sp
        }
        else -> {
            headLineTextSize  = 20.sp
            infoTextSize  = 25.sp
        }
    }

    val jp = viewModel.getFilteredJP()
    addresses[0] = (jp?.emailAddress.toString())

    val checkedState = remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current

    Scaffold(
        topBar = {
            JPFinderTopBar(
                title = stringResource( DetailsScreenDestination.titleRes),
                navigateUp = onNavigateUp,
                canNavigateBack = canNavigateBack,
            )
        }
    ) {

        when(configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                    Column(
                        modifier = modifier
                            .padding(it),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,


                        ) {

                        JpDetails(jp, headLineTextSize, infoTextSize)

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                        ) {

                            Button(
                                onClick = {
                                    if (jp != null) {
                                        composeEmail(
                                            address = addresses,
                                            subject = "JP Services Needed",
                                            context
                                        )
                                    }
                                },
                                shape = MaterialTheme.shapes.medium
                            )
                            {
                                Text(
                                    text = "Email JP"
                                )

                            }

                            IconToggleButton(
                                checked = checkedState.value,
                                onCheckedChange = {fave ->
                                    checkedState.value = !checkedState.value
                                    if (jp != null) {
                                        viewModel.setFavoriteJP(fave, jp)
                                    }

                                },
                                modifier = Modifier.padding(10.dp)
                            )
                            {

                                val transition = updateTransition(checkedState.value,
                                    label = "Transition State"
                                )
                                val tint by transition.animateColor(label = "iconColor") { isChecked ->
                                    // if toggle button is checked we are setting color as red.
                                    // in else condition we are setting color as black
                                    if (isChecked) Color.Red else Color.Blue
                                }

                                val size by transition.animateDp(
                                    transitionSpec = {
                                        // on below line we are specifying transition
                                        if (false isTransitioningTo true) {
                                            // on below line we are specifying key frames
                                            keyframes {
                                                // on below line we are specifying animation duration
                                                durationMillis = 250
                                                // on below line we are specifying animations.
                                                30.dp at 0 with LinearOutSlowInEasing // for 0-15 ms
                                                35.dp at 15 with FastOutLinearInEasing // for 15-75 ms
                                                40.dp at 75 // ms
                                                35.dp at 150 // ms
                                            }
                                        } else {
                                            spring(stiffness = Spring.StiffnessVeryLow)
                                        }
                                    },
                                    label = "Size"
                                ) {
                                    30.dp
                                }

                                Icon(
                                    // on below line we are specifying icon for our image vector.
                                    imageVector = if (checkedState.value) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                    contentDescription = "Icon",
                                    // on below line we are specifying
                                    // tint for our icon.
                                    tint = tint,
                                    // on below line we are specifying
                                    // size for our icon.
                                    modifier = Modifier.size(size)
                                )

                            }


                        }
                        Column(
                            verticalArrangement = Arrangement.Bottom,
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            bannerAds(context = context)
                        }

                    }
            } else ->{
                Row(
                    modifier = modifier
                        .padding(it),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,


                    ) {

                    JpDetailsWide(jp, headLineTextSize, infoTextSize)

                    Spacer(modifier = Modifier.weight(1f))
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.Start
                    ) {


                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Top,
                            modifier = Modifier.padding(start = 20.dp, top = 20.dp)
                        ) {

                            Button(
                                onClick = {
                                    if (jp != null) {
                                        composeEmail(
                                            address = addresses,
                                            subject = "JP Services Needed",
                                            context
                                        )
                                    }
                                },
                                shape = MaterialTheme.shapes.medium
                            )
                            {
                                Text(
                                    text = "Email JP"
                                )

                            }


                                IconToggleButton(
                                    checked = checkedState.value,
                                    onCheckedChange = {fave ->
                                        checkedState.value = !checkedState.value
                                        if (jp != null) {
                                            viewModel.setFavoriteJP(fave, jp)
                                        }

                                                      },
                                    modifier = Modifier.padding(10.dp)
                                )
                                {

                                    val transition = updateTransition(checkedState.value,
                                        label = "Transition State"
                                    )
                                    val tint by transition.animateColor(label = "iconColor") { isChecked ->
                                        // if toggle button is checked we are setting color as red.
                                        // in else condition we are setting color as black
                                        if (isChecked) Color.Red else Color.Blue
                                    }

                                    val size by transition.animateDp(
                                        transitionSpec = {
                                            // on below line we are specifying transition
                                            if (false isTransitioningTo true) {
                                                // on below line we are specifying key frames
                                                keyframes {
                                                    // on below line we are specifying animation duration
                                                    durationMillis = 250
                                                    // on below line we are specifying animations.
                                                    30.dp at 0 with LinearOutSlowInEasing // for 0-15 ms
                                                    35.dp at 15 with FastOutLinearInEasing // for 15-75 ms
                                                    40.dp at 75 // ms
                                                    35.dp at 150 // ms
                                                }
                                            } else {
                                                spring(stiffness = Spring.StiffnessVeryLow)
                                            }
                                        },
                                        label = "Size"
                                    ) {
                                        30.dp
                                    }

                                    Icon(
                                        // on below line we are specifying icon for our image vector.
                                        imageVector = if (checkedState.value) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                        contentDescription = "Icon",
                                        // on below line we are specifying
                                        // tint for our icon.
                                        tint = tint,
                                        // on below line we are specifying
                                        // size for our icon.
                                        modifier = Modifier.size(size)
                                    )

                                }
                            Column(
                                verticalArrangement = Arrangement.Bottom,
                                modifier = Modifier.fillMaxHeight()
                                    .padding(end = 10.dp, bottom = 5.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                bannerAds(context = context)
                            }
                        }
                    }


                }
            }
        }
    }
}



@Composable
fun JpDetails(jp: JP?,headLineTextSize: TextUnit,infoTextSize: TextUnit, modifier: Modifier = Modifier) {

       LazyColumn(
           modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)

           ) {
           item {
               Card(
                   modifier = Modifier
                       .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
                       .fillMaxWidth()
                       .fillMaxHeight(),
                   elevation = CardDefaults.cardElevation(4.dp),
                   colors = CardDefaults.elevatedCardColors(
                       MaterialTheme.colorScheme.primary,
                       MaterialTheme.colorScheme.onPrimary
                   )
               ) {
                   Column(
                       horizontalAlignment = Alignment.CenterHorizontally,
                       modifier = Modifier.padding(start = 8.dp, bottom = 12.dp,),
                       verticalArrangement = Arrangement.Center

                       //verticalArrangement = Arrangement.Center
                   ) {

                       Column(
                           horizontalAlignment = Alignment.CenterHorizontally,
                           verticalArrangement = Arrangement.Center,
                           modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                       ) {
                           Icon(imageVector = Icons.Rounded.AccountBox, contentDescription = "JP Icon", modifier = Modifier
                               .size(100.dp))
                       }
                       Row(
                           horizontalArrangement = Arrangement.spacedBy(10.dp),
                           verticalAlignment = Alignment.CenterVertically,
                           modifier = Modifier.padding(bottom = 10.dp)
                       ) {
                           Text(
                               "${jp?.firstName}",
                               fontSize = infoTextSize
                           )

                           Text(
                               "${jp?.lastName}",
                               fontSize = infoTextSize
                           )
                       }
                       //}
                       Divider()
//                Column() {
//                    Text(
//                        "MiddleName: ",
//                        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
//                        //fontSize = headLineTextSize
//                    )
//                    Text("${jp?.middleName}",
//                        //fontSize = infoTextSize
//                    )
//                }
                       //Divider()
                       Row(
                           horizontalArrangement = Arrangement.spacedBy(10.dp),
                           verticalAlignment = Alignment.CenterVertically,
                           modifier = Modifier.padding(bottom = 10.dp)
                       ) {
                           Text(
                               "${jp?.community}",
                               fontSize = headLineTextSize
                           )
                       }

                       Divider()

                       Row(
                           horizontalArrangement = Arrangement.spacedBy(10.dp),
                           verticalAlignment = Alignment.CenterVertically,
                           modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                       ) {
                           Text(
                               "${jp?.emailAddress}",
                               modifier = Modifier.clickable { },
                               fontSize = headLineTextSize
                           )
                       }

                       Divider()


                   }

               }

           }
       }


}

@Composable
fun JpDetailsWide(jp: JP?,headLineTextSize: TextUnit,infoTextSize: TextUnit, modifier: Modifier = Modifier) {

    LazyColumn(
        modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)

    ) {
        item {
            Card(
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
                    .fillMaxWidth(.5f)
                    .fillMaxHeight(),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.elevatedCardColors(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(start = 8.dp, bottom = 12.dp,),
                    verticalArrangement = Arrangement.Center

                    //verticalArrangement = Arrangement.Center
                ) {

                   Column(
                       horizontalAlignment = Alignment.CenterHorizontally,
                       verticalArrangement = Arrangement.Center,
                       modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                   ) {
                       Icon(imageVector = Icons.Rounded.AccountBox, contentDescription = "JP Icon", modifier = Modifier
                           .size(100.dp))
                   }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 10.dp)
                    ) {
                        Text(
                            "${jp?.firstName}",
                            fontSize = infoTextSize
                        )

                        Text(
                            "${jp?.lastName}",
                            fontSize = infoTextSize
                        )
                    }
                    //}
                     Divider()
//                Column() {
//                    Text(
//                        "MiddleName: ",
//                        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
//                        //fontSize = headLineTextSize
//                    )
//                    Text("${jp?.middleName}",
//                        //fontSize = infoTextSize
//                    )
//                }
                    //Divider()
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 10.dp)
                    ) {
                        Text(
                            "${jp?.community}",
                            fontSize = headLineTextSize
                        )
                    }

                Divider()

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                    ) {
                        Text(
                            "${jp?.emailAddress}",
                            modifier = Modifier.clickable { },
                            fontSize = headLineTextSize
                        )
                    }

                    Divider()


                }

            }

        }
    }

}


private fun composeEmail(address: Array<String>, subject: String, context: Context) {

    val packageManager = context.packageManager
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:") // only email apps should handle this
        putExtra(Intent.EXTRA_EMAIL, address)
        putExtra(Intent.EXTRA_SUBJECT, subject)
    }
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(context,intent,null)
    }
}




//@Preview(showBackground = true,
//    showSystemUi = true)
//@Composable
//fun DetailsScreenPreview() {
//    JPFinderTheme {
//
//        val jp = JP(1,"Smith","Stephen","George","804 Salem Path","Westgreen","Westgreen","smitstephen@gmail.com")
//        JpDetails(jp,15.sp, 18.sp)
//    }
//}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DetailsScreenWidePreview() {
    JPFinderTheme {

        val jp = JP(1,"Smith","Stephen","George","804 Salem Path","Westgreen","Westgreen","smitstephen@gmail.com")
        JpDetailsWide(jp,25.sp, 30.sp)
    }
}

//@Preview(showBackground = true,
//    showSystemUi = true)
//@Composable
//fun DetailsScreenPreview1() {
//    JPFinderTheme {
//        //DetailsScreen({ } ,1, true)
//    }
//}