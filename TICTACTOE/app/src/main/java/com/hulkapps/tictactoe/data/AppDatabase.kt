package com.hulkapps.tictactoe.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hulkapps.tictactoe.data.dao.PlayerDao
import com.hulkapps.tictactoe.data.models.Player


@Database(entities = arrayOf(Player::class), version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun playerDao(): PlayerDao



    companion object{

        private var INSTANCE: AppDatabase? = null

        fun setInstance(appdb:AppDatabase):Unit{
            INSTANCE=appdb
        }

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildRoomDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "HATicTacToe"
            ).build()
    }

}