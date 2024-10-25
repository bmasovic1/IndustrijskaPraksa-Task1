package com.hulkapps.tictactoe.data.repositories

import com.hulkapps.tictactoe.data.models.Board

class BoardRepository {

    companion object{

        private var board : Board
        private var boardAi : Board
        var pl1 : Boolean
        var creator : Boolean
        var room : String

        init {
            board = Board(0,0,0,0,0,0,0,0, 0, true,0,true, true, "","", 0,0,0,0)
            boardAi = Board(0,0,0,0,0,0,0,0, 0, true,0,true,true, "","",0,0,0,0)
            pl1=true
            creator=false
            room = ""
        }


        fun boardActive(){
            board.active=false
        }

        fun getBoard(): Board {
            return board
        }

        fun getBoardAi(): Board {
            return boardAi
        }

        fun restartBoard(){
            board.a1=0;board.a2=0;board.a3=0
            board.b1=0;board.b2=0;board.b3=0
            board.c1=0;board.c2=0;board.c3=0
            board.active=true
        }

        fun restartPvPBoard(){
            board.a1=0;board.a2=0;board.a3=0
            board.b1=0;board.b2=0;board.b3=0
            board.c1=0;board.c2=0;board.c3=0
            board.active=true
            board.pl1=true
            board.turn=true
        }

        fun restartBoardAi(){
            boardAi.a1=0;boardAi.a2=0;boardAi.a3=0
            boardAi.b1=0;boardAi.b2=0;boardAi.b3=0
            boardAi.c1=0;boardAi.c2=0;boardAi.c3=0
            boardAi.active=true
        }

        fun isEmpty(): Boolean{
            return  board.a1==0 && board.a2==0 && board.a3==0 &&
                    board.b1==0 && board.b2==0 && board.b3==0 &&
                    board.c1==0 && board.c2==0 && board.c3==0
        }

        fun isEmptyAi(): Boolean{
            return  boardAi.a1==0 && boardAi.a2==0 && boardAi.a3==0 &&
                    boardAi.b1==0 && boardAi.b2==0 && boardAi.b3==0 &&
                    boardAi.c1==0 && boardAi.c2==0 && boardAi.c3==0
        }

        fun emptySpaces(): Int{
            var rez=0
            if(board.a1==0)rez++
            if(board.a2==0)rez++
            if(board.a3==0)rez++
            if(board.b1==0)rez++
            if(board.b2==0)rez++
            if(board.b3==0)rez++
            if(board.c1==0)rez++
            if(board.c2==0)rez++
            if(board.c3==0)rez++
            return  rez
        }

        fun emptySpacesAi(): Int{
            var rez=0
            if(boardAi.a1==0)rez++
            if(boardAi.a2==0)rez++
            if(boardAi.a3==0)rez++
            if(boardAi.b1==0)rez++
            if(boardAi.b2==0)rez++
            if(boardAi.b3==0)rez++
            if(boardAi.c1==0)rez++
            if(boardAi.c2==0)rez++
            if(boardAi.c3==0)rez++
            return  rez
        }

    }

}