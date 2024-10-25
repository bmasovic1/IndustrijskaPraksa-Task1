package com.hulkapps.tictactoe.data.repositories

import android.content.Context
import android.util.Log
import com.hulkapps.tictactoe.data.AppDatabase
import com.hulkapps.tictactoe.data.models.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlayerRepository {

    companion object{

        private lateinit var context: Context

        fun setContext(conte: Context){
            context = conte
        }

        private var player1 : Player
        private var player2 : Player
        private var playerAi : Player

        init {
            player1 = Player(0,"Player1", 0, 0,"Classic",true)
            player2 = Player(1,"Player2", 0, 0,"Classic",false)
            playerAi = Player(3,"Ai", 0, 0,"Classic",false)
        }

        fun getPlayer1():Player{
            return player1
        }

        fun getPlayer2():Player{
            return player2
        }

        fun getPlayerAi():Player{
            return playerAi
        }

        fun setPlayer1Name(name: String){
            player1.name=name
        }

        fun setPlayer2Name(name: String){
            player2.name=name
        }

        fun setPlayerAiName(name: String){
            playerAi.name=name
        }

        fun addOnPlayerScore(player: Player){
            player.score++
        }

        fun setPlayerScore(player: Player, score: Int){
            player.score=score
        }

        suspend fun addOnPlayr(player: Player) {

            val db = AppDatabase.getInstance(context)
            db.playerDao().insertOne(player)

        }

        suspend fun updatePlayer(player: Player) {

            val db = AppDatabase.getInstance(context)
            db.playerDao().updatePlayer(player)
            player1 = db.playerDao().getPlayer1()
            playerAi = db.playerDao().getPlayer1()
            Log.e("UPDATE PLAYER ", player.name)

        }

        suspend fun setPlayer() {
            val db = AppDatabase.getInstance(context)
            player1 = db.playerDao().getPlayer1()
        }

        suspend fun setPlayerAi() {
            val db = AppDatabase.getInstance(context)
            playerAi = db.playerDao().getPlayerAi()
        }

        suspend fun setPlayer2() {
            val db = AppDatabase.getInstance(context)
            player2 = db.playerDao().getPlayer2()
        }


        suspend fun getCount(): Int {

            val db = AppDatabase.getInstance(context)
            return db.playerDao().getAll()

        }


    }

}