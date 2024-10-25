package com.hulkapps.tictactoe.viewmodel

import com.hulkapps.tictactoe.data.models.Board
import com.hulkapps.tictactoe.data.repositories.BoardRepository

class BoardViewModel {

    fun getBoard(): Board {
        return BoardRepository.getBoard()
    }

    fun restartBoard(){
        BoardRepository.restartBoard()
    }

    fun isEmpty(): Boolean{
       return BoardRepository.isEmpty()
    }

    fun boardActive(){
        BoardRepository.boardActive()
    }

    fun emptySpaces(): Int{
        return BoardRepository.emptySpaces()
    }

    fun restartPvPBoard(){
        BoardRepository.restartPvPBoard()
    }

    fun getBoardAi(): Board {
        return BoardRepository.getBoardAi()
    }

    fun restartBoardAi(){
        BoardRepository.restartBoardAi()
    }

    fun isEmptyAi(): Boolean{
        return BoardRepository.isEmptyAi()
    }

    fun emptySpacesAi(): Int{
        return BoardRepository.emptySpacesAi()
    }
}