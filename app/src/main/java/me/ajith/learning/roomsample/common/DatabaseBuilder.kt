package me.ajith.learning.roomsample.common

import android.content.Context
import androidx.room.Room
import me.ajith.learning.roomsample.data.local.UserDatabase

object DatabaseBuilder {

    private var INSTANCE: UserDatabase?=null

    fun getDbInstance(context: Context): UserDatabase {
        if(INSTANCE ==null){
            synchronized(UserDatabase::class.java){
                if (INSTANCE ==null){
                    INSTANCE = buildDb(context)
                }
            }
        }
        return INSTANCE!!
    }

    private fun buildDb(context: Context): UserDatabase {
        return Room.databaseBuilder(context, UserDatabase::class.java,"user_database").build()
    }
}