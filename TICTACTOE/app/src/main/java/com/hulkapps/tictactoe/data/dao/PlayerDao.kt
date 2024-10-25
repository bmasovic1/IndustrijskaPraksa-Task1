package com.hulkapps.tictactoe.data.dao

import androidx.room.Insert
import androidx.room.Query
import androidx.room.*
import com.hulkapps.tictactoe.data.models.Player

@Dao
interface PlayerDao {

    @Query("DELETE FROM player")
    suspend fun delete()

    @Insert
    suspend fun insertOne(player : Player)

    @Update
    suspend fun updatePlayer(player: Player): Int

    @Query("SELECT COUNT(*) FROM Player")
    suspend fun getAll(): Int

    @Query("SELECT * FROM player WHERE id = 1")
    suspend fun getPlayer1(): Player

    @Query("SELECT * FROM player WHERE id = 2")
    suspend fun getPlayer2(): Player

    @Query("SELECT * FROM player WHERE id = 3")
    suspend fun getPlayerAi(): Player

}