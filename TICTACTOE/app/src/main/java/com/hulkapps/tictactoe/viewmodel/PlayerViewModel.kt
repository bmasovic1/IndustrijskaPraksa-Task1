package com.hulkapps.tictactoe.viewmodel

import android.content.Context
import com.hulkapps.tictactoe.data.models.Player
import com.hulkapps.tictactoe.data.repositories.PlayerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PlayerViewModel {

    val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun setContext(context: Context){
         PlayerRepository.setContext(context)
    }

    suspend fun addPlayer(player : Player){
        scope.launch {
            PlayerRepository.addOnPlayr(player)
        }
    }

    suspend fun updatePlayer(player : Player){
        scope.launch {
            PlayerRepository.updatePlayer(player)
        }
    }

    fun setPlayer1(){
        scope.launch{
            PlayerRepository.setPlayer()
        }
    }

    fun setPlayer2(){
        scope.launch{
            PlayerRepository.setPlayer2()
        }
    }

    fun setPlayerAi(){
        scope.launch{
            PlayerRepository.setPlayerAi()
        }
    }


    fun getPlayer1(): Player {
        return PlayerRepository.getPlayer1()
    }

    fun getPlayer2(): Player {
        return PlayerRepository.getPlayer2()
    }

    fun getPlayerAi(): Player {
        return PlayerRepository.getPlayerAi()
    }



    fun addOnPlayerScore(player:Player) {
        PlayerRepository.addOnPlayerScore(player)
    }

    fun setPlayer1Name(name: String){
        PlayerRepository.setPlayer1Name(name)
    }

    fun setPlayer2Name(name: String){
        PlayerRepository.setPlayer2Name(name)
    }

    fun setPlayerAiName(name: String){
        PlayerRepository.setPlayerAiName(name)
    }

    fun setPlayerScore(player:Player, score: Int){
        PlayerRepository.setPlayerScore(player,score)
    }

    suspend fun getCount(): Int {
        return PlayerRepository.getCount()
    }

}