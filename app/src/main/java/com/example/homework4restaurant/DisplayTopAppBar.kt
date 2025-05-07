package com.example.homework4restaurant

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayTopAppBar(navController: NavHostController) {
    val context = LocalContext.current
    var showMenu by remember { mutableStateOf(false) }
    val navHostController = rememberNavController()


    CenterAlignedTopAppBar (
        title = { Text(text = "Restaurant App") },
        navigationIcon = {
            IconButton(onClick = {
                navHostController.navigate("SettingsScreen")
            })

            {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "backIcon")
            } // end - IconButton
        },
        actions = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(Icons.Default.MoreVert, contentDescription = null)
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                DropdownMenuItem(
                    text= { Text("Settings") },
                    onClick = {
// Action 1 click handler code goes here
                        showMenu = false
                        Toast.makeText(context,"Settings Screen", Toast.LENGTH_SHORT).show()
                        navController.navigate("SettingsScreen")
                    },
                    leadingIcon = { Icon(Icons.Filled.Settings, contentDescription = null) })
                //Drop down button 2
            } // end - DropdownMenu
        } // end - actions// end - navigationIcon
    )
}