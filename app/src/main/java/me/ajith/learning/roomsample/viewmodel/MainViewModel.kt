package me.ajith.learning.roomsample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.ajith.learning.roomsample.data.User
import me.ajith.learning.roomsample.data.local.DatabaseHelper

class MainViewModel(
    private val databaseHelper:DatabaseHelper
):ViewModel() {

    private var userData = MutableLiveData<MutableList<User>?>()

    private val userLiveData: MutableLiveData<MutableList<User>?> get() = userData

    init {
        viewModelScope.launch {
            var existingData = userData.value
            if (existingData == null) {
                existingData = arrayListOf()
            }
            existingData.addAll(databaseHelper.getAllUsers())
            userData.postValue(existingData)
        }
    }



    fun getUserData(): MutableLiveData<MutableList<User>?> {
        return userLiveData
    }

    fun addUserData(user:User){
        viewModelScope.launch {
            var newUserData = userData.value
            if (newUserData == null) {
                newUserData = arrayListOf()
            }
            newUserData.add(user)
            databaseHelper.insertUsers(user)
//            databaseHelper.getAllUsers().map {
//                println("Data from Database : ${it.userName} - ${it.userNickname}")
//            }
            newUserData.clear()
            newUserData.addAll(databaseHelper.getAllUsers())
            userData.postValue(newUserData)
        }
    }

}