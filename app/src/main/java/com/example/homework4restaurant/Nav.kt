package com.example.homework4restaurant

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Nav(navHostController: NavHostController, modifier: Modifier) {
    //  val nav = rememberNavController()
    NavHost(navController=navHostController, startDestination = "RestaurantScreen", modifier) {
        composable(route="RestaurantScreen") {
            RestaurantScreen(modifier)
        }
        composable(route="AddRestaurantScreen") {
            AddRestaurantScreen(modifier)
        }
        composable(route="SettingsScreen") {
            SettingsScreen(modifier)
        }
    }
}