package com.example.homework4restaurant

import android.app.Application
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class RestaurantApplication : Application() {

    companion object {
        lateinit var repository: RestaurantRepository
            private set
        lateinit var restaurantDao: RestaurantDao
            private set
    }

    override fun onCreate() {
        super.onCreate()

        runBlocking(Dispatchers.IO) {
            //  Create Preferences DataStore
            val myPreferences = MyPreferences(applicationContext)

            //  Create Room Database
            val db = Room.databaseBuilder(
                applicationContext,
                RestaurantDatabase::class.java,
                "restaurant.db"
            ).build()

            restaurantDao = db.restaurantDao()!!

            //  Create Repository
            repository = RestaurantRepository(restaurantDao, myPreferences)
       }
    }
}
