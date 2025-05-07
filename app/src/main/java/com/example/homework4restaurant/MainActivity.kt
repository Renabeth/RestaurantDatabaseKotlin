package com.example.homework4restaurant

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.homework4restaurant.ui.theme.Homework4RestaurantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {




            val navItemsList = listOf(
                MyNavItem(
                    title = "Restaurants",
                    iconSelected = Icons.Filled.Home,
                    iconUnselected = Icons.Outlined.Home,
                    route = "RestaurantScreen"
                ),
                MyNavItem(
                    title = "Add Restaurant",
                    iconSelected = Icons.Filled.Add,
                    iconUnselected = Icons.Outlined.Add,
                    route = "AddRestaurantScreen"
                )


            )

            var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
            val navHostController = rememberNavController()
            val navBackStackEntry by navHostController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val context = LocalContext.current

            Homework4RestaurantTheme {
                Scaffold(

                    topBar = {
                        //DisplayTopAppBar()
                        DisplayTopAppBar(navController = navHostController)
                        MyFAB()
                    },

                    bottomBar = {
                        NavigationBar {
                            navItemsList.forEachIndexed { index, item ->
                                NavigationBarItem(
                                    selected = currentDestination?.hierarchy?.any {
                                        it.route.equals(
                                            item.route
                                        )
                                    } == true,
                                    onClick = {
                                        selectedItemIndex = index
                                        navHostController.navigate(item.route) {
                                            popUpTo(navHostController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        } // end - navigate
                                        Toast.makeText(context, item.title, Toast.LENGTH_SHORT)
                                            .show()
                                    },
                                    label = { Text(text = item.title) },
                                    icon = {
                                        Icon(
                                            contentDescription = item.title,
                                            imageVector = if (index == selectedItemIndex) item.iconSelected
                                            else item.iconUnselected
                                        )
                                    }
                                ) // end – NavigationBarItem
                            } // end – forEachIndexed
                        } // end - NavigationBar

                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Nav(
                        navHostController = navHostController,
                        Modifier.padding(innerPadding)

                    )



                    }
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        Homework4RestaurantTheme {
            Greeting("Android")
        }
    }

    @Composable
    fun MyFAB() {
        FloatingActionButton(
            onClick = {
// FAB click handler code goes here
            },
        ) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }
    }
