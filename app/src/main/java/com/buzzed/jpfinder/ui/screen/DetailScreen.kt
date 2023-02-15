package com.buzzed.jpfinder.ui.screen

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.buzzed.jpfinder.JPFinderTopBar
import com.buzzed.jpfinder.R
import com.buzzed.jpfinder.navigation.NavigationDestination
import com.buzzed.jpfinder.ui.theme.JPFinderTheme
import kotlinx.coroutines.delay

object DetailsScreenDestination: NavigationDestination {
    override val route = "details_screen"
    override val titleRes = R.string.jp_details
    var jpId = 0

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    onNavigateUp: () -> Unit,
    id: Int,
    modifier: Modifier = Modifier,
    viewModel: DetailsScreenViewModel = viewModel(factory = AppViewModelProvider.factory),

    ) {
    val context = LocalContext.current
    val addresses = arrayOf("")
    val headLineTextSize = 18.sp
    val infoTextSize = 19.sp

    val jp = viewModel.getFilteredJP()
    addresses[0] = (jp?.emailAddress.toString())

    Scaffold(
        topBar = {
            JPFinderTopBar(
                title = DetailsScreenDestination.titleRes,
                navigateUp = onNavigateUp,
                canNavigateBack = true,
            )
        }
    ) {

        Column(
            modifier = modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.elevatedCardColors(MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.padding(start = 8.dp, bottom = 12.dp)
                        //verticalArrangement = Arrangement.Center
                    ) {

                        Column(modifier = modifier) {

                            Text(
                                "FirstName:",
                                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                                fontSize = headLineTextSize,

                            )

                            Text("${jp?.firstName}",
                                fontSize = infoTextSize
                            )

                        }
                        Divider()
                        Column() {
                            Text(
                                "MiddleName: ",
                                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                                fontSize = headLineTextSize
                            )
                            Text("${jp?.middleName}",
                                fontSize = infoTextSize
                            )
                        }
                        Divider()
                        Column() {
                            Text(
                                "LastName: ",
                                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                                fontSize = headLineTextSize
                            )
                            Text("${jp?.lastName}",
                                fontSize = infoTextSize
                            )
                        }
                        Divider()
                        Column() {
                            Text(
                                "Address1: ",
                                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                                fontSize = headLineTextSize
                            )
                            Text("${jp?.address1}",
                                fontSize = infoTextSize
                            )

                        }
                        Divider()
                        Column() {
                            Text(
                                "Address2: ",
                                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                                fontSize = headLineTextSize
                            )
                            Text("${jp?.address2}",
                                fontSize = infoTextSize
                            )
                        }
                        Divider()
                        Column() {
                            Text(
                                "Community: ",
                                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                                fontSize = headLineTextSize
                            )
                            Text("${jp?.community}",
                                fontSize = infoTextSize
                            )
                        }
                        Divider()
                        Column() {
                            Text(
                                "Email Address: ",
                                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                                fontSize = headLineTextSize
                            )
                            Text("${jp?.emailAddress}",
                                modifier = Modifier.clickable { },
                                fontSize = infoTextSize
                            )
                        }
                        Divider()
                    }

            }
            Button(
                onClick = {
                    if (jp != null) {
                        composeEmail(address = addresses, subject = "JP Services Needed",context)
                    }
                })
            {
                Text(
                    text = "Email JP"
                )

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



@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun DetailsScreenPreview() {
    JPFinderTheme {

        DetailsScreen({ } ,0, modifier = Modifier)
    }
}

@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun DetailsScreenPreview1() {
    JPFinderTheme {
        DetailsScreen({ } ,1)
    }
}