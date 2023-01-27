package com.buzzed.jpfinder.ui.screen

import android.view.Menu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buzzed.jpfinder.data.Parish
import com.buzzed.jpfinder.data.Parishes
import com.buzzed.jpfinder.ui.theme.JPFinderTheme




//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    var parishTextValue by rememberSaveable { mutableStateOf("") }
    var communityTextValue by rememberSaveable { mutableStateOf("") }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        HomeScreenDetails(parishTextValue, communityTextValue)

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenDetails(
    parishTextValue: String,
    communityTextValue: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier.padding(top = 50.dp, bottom = 50.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

        var expanded by remember { mutableStateOf(false)}

        val icon = if (expanded)
            Icons.Filled.ArrowForward //it requires androidx.compose.material:material-icons-extended
        else
            Icons.Filled.ArrowDropDown

        Column(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

                Text(
                    text = "Please Enter Your Parish"
                )
                LocationListDropdown(Parishes(), parishTextValue, icon)
                LocationListDropdown(listOf(), communityTextValue, icon)

                Button(
                    modifier = Modifier,
                    onClick = { /*TODO*/ },
                    enabled = false
                ) {
                    Text(
                        text = "Find JPs",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationListDropdown(
    listItems: List<Parish> ,
    locationText: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false)}

                OutlinedTextField(
                    value = "List of Parishes" ,
                    onValueChange = {})
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { /*TODO*/ }) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = { Text(text = locationText)}
                    )
                    DropdownMenuItem(
                        text = { for(parish in listItems) {parish.name} },
                        onClick = { /*TODO*/ },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)

                        }

                        )
                }
}






@Composable
fun ParishList(parishes: List<Parish>) {
    LazyColumn {
        items(parishes) { parish ->
            ParishRow(parish)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParishRow(parish: Parish) {
    Row() {
        Card() {
            Text(
                text = stringResource(parish.name)
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    JPFinderTheme {
        HomeScreen()
    }
}