package com.example.homework4restaurant

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RestaurantScreenViewModel(var applicationContext: Application) : AndroidViewModel(applicationContext) {

    private val myPreferences: MyPreferences
    var restaurantList = mutableStateOf<List<Restaurant>>(emptyList())
        private set
    var restaurantDB:RestaurantDatabase

    val showBackgroundPicStateFlow: StateFlow<Boolean>

    init {
        val context: Context = getApplication<Application>().applicationContext
        myPreferences = MyPreferences(context)
        showBackgroundPicStateFlow =
            myPreferences.watchShowPic().stateIn(viewModelScope, SharingStarted.Lazily, false)

        restaurantDB = Room.databaseBuilder(
            applicationContext,
            RestaurantDatabase::class.java, "restaurant"
        ).build()

        viewModelScope.launch(Dispatchers.IO) {
            fillDBWithData()
            restaurantDB.restaurantDao()!!.selectAll()!!
                .collect { currentRestaurantList ->
                    restaurantList.value = currentRestaurantList
                }
        }
    }


    fun toggleShowBackgroundPic() {
        viewModelScope.launch {
            val newShowPicValue: Boolean = !showBackgroundPicStateFlow.value
            myPreferences.updateShowPic(newShowPicValue)
        }
    }

    suspend fun fillDBWithData() {
        restaurantDB.restaurantDao()?.deleteAll()
        val r1 = Restaurant("The Melting Pot", "Farmingdale",4.0)
        restaurantDB.restaurantDao()!!.insert(r1)
        val r2 = Restaurant("Burger King", "Farmingdale", 2.0)
        restaurantDB.restaurantDao()!!.insert(r2)
        val r3 = Restaurant("The Lakehouse", "Bay Shore", 4.5)
        restaurantDB.restaurantDao()!!.insert(r3)
        val r4 = Restaurant("Miller's Ale House", "Levittown", 3.5)
        restaurantDB.restaurantDao()!!.insert(r4)
        val r5 = Restaurant("Secret Thai Kitchen", "Freeport", 4.0)
        restaurantDB.restaurantDao()!!.insert(r5)
        val r6 = Restaurant("Cafe Continental", "Manhasset", 4.0)
        restaurantDB.restaurantDao()!!.insert(r6)
        val r7 = Restaurant("Friendly's", "Stony Brook", 2.5)
        restaurantDB.restaurantDao()!!.insert(r7)
    }
}