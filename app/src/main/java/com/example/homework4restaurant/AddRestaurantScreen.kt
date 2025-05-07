package com.example.homework4restaurant

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddRestaurantScreen(modifier: Modifier) {

    var name by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("") }
    val context = LocalContext.current

    Surface(
        Modifier.padding(10.dp).fillMaxSize(),
        shape = RoundedCornerShape(10.dp),
        shadowElevation = 30.dp
    ) {

        Column(modifier) {
            Text(
                "Add Restaurant",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )


            TextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
            TextField(value = location, onValueChange = { location = it }, label = { Text("Location") })
            TextField(
                value = rating,
                onValueChange = { rating = it },
                label = { Text("Rating") })

            Button(onClick = {
                Toast.makeText(context, "Restaurant added", Toast.LENGTH_SHORT).show()
            }) {
                Text("Add Restaurant")
            }
        }
    }
}