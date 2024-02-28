package me.ajith.learning.roomsample.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import me.ajith.learning.roomsample.data.User

@Dao
interface UserDao {
    @Query("Select * from user order by 1 desc")
    suspend fun getAllUsers():List<User>

    @Insert
    suspend fun insertUsers(user: User)
}