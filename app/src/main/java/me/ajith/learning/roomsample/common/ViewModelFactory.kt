package me.ajith.learning.roomsample.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.ajith.learning.roomsample.data.local.DatabaseHelper
import me.ajith.learning.roomsample.viewmodel.MainViewModel

class ViewModelFactory(private val databaseHelper: DatabaseHelper):ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(databaseHelper) as T
        }
        throw IllegalArgumentException("Unknown Class Name")
    }
}