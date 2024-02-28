package me.ajith.learning.roomsample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import me.ajith.learning.roomsample.data.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase:RoomDatabase() {
    abstract fun getUserDao():UserDao
}