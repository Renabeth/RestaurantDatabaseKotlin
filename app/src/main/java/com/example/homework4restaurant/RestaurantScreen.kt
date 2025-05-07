package com.example.homework4restaurant

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


//I was having an issue with the information from the database displaying on the emulator, but it seemed to work fine on my phone. Unsure if that will be the case for your machine. Thank you
@Composable
fun RestaurantScreen(modifier: Modifier) {
   // val viewModel = RestaurantScreenViewModel(LocalContext.current.applicationContext as Application)
    val context = LocalContext.current
    val viewModel = androidx.lifecycle.viewmodel.compose.viewModel { RestaurantScreenViewModel(context.applicationContext as Application) }

    val showPicBackgroundPicLocal: Boolean by viewModel.showBackgroundPicStateFlow.collectAsState()

    lateinit var restaurantDB:RestaurantDatabase
    val restaurantList = viewModel.restaurantList.value


    Surface(Modifier.padding(10.dp).fillMaxWidth(), shape = RoundedCornerShape(10.dp), shadowElevation = 30.dp) {

        Column(modifier) {

            Text("Restaurants")
            Button(onClick = { viewModel.toggleShowBackgroundPic() }

            )
            {
                Text(text = "Toggle Ratings On/Off")
            }
            Text("Show Background Pic Value = " + showPicBackgroundPicLocal.toString())

            for (restaurant in restaurantList) {
                Text(restaurant.name)
                Text("Location: " + restaurant.location.toString())
                Text("Rating: "+ restaurant.rating.toString())

            }
        }

    }
}

