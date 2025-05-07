package com.example.homework4restaurant

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.lang.Integer.parseInt


//I was having an issue with the information from the database displaying on the emulator, but it seemed to work fine on my phone. Unsure if that will be the case for your machine. Thank you
@Composable
fun RestaurantScreen(modifier: Modifier) {
    // val viewModel = RestaurantScreenViewModel(LocalContext.current.applicationContext as Application)
    val context = LocalContext.current
    val viewModel =
        androidx.lifecycle.viewmodel.compose.viewModel { RestaurantScreenViewModel(context.applicationContext as Application) }

    val showPicBackgroundPicLocal: Boolean by viewModel.showBackgroundPicStateFlow.collectAsState()

    lateinit var restaurantDB: RestaurantDatabase
    val restaurantList by remember { viewModel.restaurantList }




        Surface(Modifier.padding(10.dp).fillMaxSize(), shape = RoundedCornerShape(10.dp), shadowElevation = 30.dp) {


            LazyColumn() {

                items(restaurantList) { currentItem ->

                    Button(onClick = { viewModel.toggleShowBackgroundPic() })
                    {
                        Text(text = "Toggle Ratings On/Off")
                    }

                    Text("Show Background Pic Value = " + showPicBackgroundPicLocal.toString())
                   // Text("Restaurants", color = MaterialTheme.colorScheme.primary, fontSize = 28.sp, fontWeight = FontWeight.Bold)


                    Column(modifier = Modifier.padding(8.dp)) {

                        Text("Name: ${currentItem.name}", color = MaterialTheme.colorScheme.primary)
                        Text("Location: ${currentItem.location}", color= Color.Magenta)
                        Text("Rating: ${currentItem.rating}", color= Color.Magenta)
                    }
                }
            }
        }
    }


