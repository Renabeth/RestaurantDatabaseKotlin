package com.example.homework4restaurant

import androidx.datastore.preferences.core.booleanPreferencesKey
import kotlinx.coroutines.flow.Flow

class RestaurantRepository(val restaurantDao: RestaurantDao, val prefDataStore: MyPreferences) {


    //Room DB Actions
    suspend fun getAllRestaurants(): Flow<List<Restaurant>> {
        return restaurantDao.getAllRestaurants()
    }
    suspend fun addRestaurant(r:Restaurant) {
        restaurantDao.insert(r)
    }

    //Preferences Datastore Actions
    //help from https://developer.android.com/topic/libraries/architecture/datastore



}