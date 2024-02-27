package me.ajith.learning.roomsample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.ajith.learning.roomsample.data.User

class MainViewModel:ViewModel() {

    private var userData = MutableLiveData<MutableList<User>?>()

    private val userLiveData: MutableLiveData<MutableList<User>?> get() = userData

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
            userData.postValue(newUserData)
        }
    }

}