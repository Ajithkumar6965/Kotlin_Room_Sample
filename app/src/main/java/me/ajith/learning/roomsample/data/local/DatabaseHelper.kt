package me.ajith.learning.roomsample.data.local

import me.ajith.learning.roomsample.data.User

interface DatabaseHelper {
    suspend fun getAllUsers():List<User>
    suspend fun insertUsers(user: User)
}