package me.ajith.learning.roomsample.data.local

import me.ajith.learning.roomsample.data.User

class DatabaseHelperImpl(private val userDatabase: UserDatabase):DatabaseHelper {
    override suspend fun getAllUsers(): List<User> = userDatabase.getUserDao().getAllUsers()

    override suspend fun insertUsers(user: User) = userDatabase.getUserDao().insertUsers(user)
}