package com.example.homework4restaurant

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen(modifier : Modifier) {

    val context = LocalContext.current
    val viewModel =
        androidx.lifecycle.viewmodel.compose.viewModel { RestaurantScreenViewModel(context.applicationContext as Application) }
    val showRatings by viewModel.showRatingsStateFlow.collectAsState()


    Column(modifier) {
        Text("Settings", color = MaterialTheme.colorScheme.primary, fontSize = 28.sp, fontWeight = FontWeight.Bold)


        Switch(
            checked = showRatings,
            onCheckedChange = {
                viewModel.toggleShowRatings()
            }
        )
        Text("Show Rating: $showRatings")
        Text("Unfortunately I could not get the bottom bar to work on this screen :( but if you use the back icon on the top left or the devices back button you can see that the ratings toggle does work.")
    }

}