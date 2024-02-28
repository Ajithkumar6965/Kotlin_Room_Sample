package me.ajith.learning.roomsample.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val userName:String="",
    val userNickname:String=""
)
