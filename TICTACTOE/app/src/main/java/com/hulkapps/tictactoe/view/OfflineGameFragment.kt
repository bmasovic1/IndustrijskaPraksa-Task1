package com.hulkapps.tictactoe.view

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.hulkapps.tictactoe.R
import com.hulkapps.tictactoe.viewmodel.BoardViewModel
import com.hulkapps.tictactoe.viewmodel.PlayerViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class OfflineGameFragment : Fragment() {

    private lateinit var pagerAdapter: ViewPagerAdapter
    var player: Boolean = true
    var turn: Int = 1
    lateinit var a1: ImageView
    lateinit var a2: ImageView
    lateinit var a3: ImageView
    lateinit var b1: ImageView
    lateinit var b2: ImageView
    lateinit var b3: ImageView
    lateinit var c1: ImageView
    lateinit var c2: ImageView
    lateinit var c3: ImageView
    lateinit var newGame: Button
    lateinit var resetScore: ImageButton
    lateinit var name1: TextView
    lateinit var name2: TextView
    lateinit var score1: TextView
    lateinit var score2: TextView

    var playerViewModel = PlayerViewModel()
    var boardViewModel = BoardViewModel()

    val plava = Color.parseColor("#19B2C6")

    var board = boardViewModel.getBoard()

    var player1 = playerViewModel.getPlayer1()
    var player2 = playerViewModel.getPlayer2()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_game, container, false)

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                pagerAdapter.add(0, PvPModeFragment.newInstance(pagerAdapter))
                pagerAdapter.remove(2)
            }
        })

        a1 = view.findViewById(R.id.a1)
        a2 = view.findViewById(R.id.a2)
        a3 = view.findViewById(R.id.a3)
        b1 = view.findViewById(R.id.b1)
        b2 = view.findViewById(R.id.b2)
        b3 = view.findViewById(R.id.b3)
        c1 = view.findViewById(R.id.c1)
        c2 = view.findViewById(R.id.c2)
        c3 = view.findViewById(R.id.c3)
        newGame = view.findViewById(R.id.NewGame)
        resetScore = view.findViewById(R.id.resetScore)
        name1 = view.findViewById(R.id.Player1)
        name2 = view.findViewById(R.id.Player2)
        score1 = view.findViewById(R.id.Player1Score)
        score2 = view.findViewById(R.id.Player2Score)

        name1.text=player1.name+":"
        name2.text=player2.name+":"
        name1.setTextColor(plava)

        score1.text=player1.score.toString()
        score2.text=player2.score.toString()

        a1.setOnClickListener(View.OnClickListener {

            if(board.active)
                if(a1.drawable == null) {
                    if (player) {
                        board.a1=1
                        setImg(1,a1)
                    }
                    else {
                        board.a1=2
                        setImg(2,a1)
                    }
                    checkForWin()
                }
        })
        a2.setOnClickListener(View.OnClickListener {

            if(board.active)
                if(a2.drawable == null) {
                    if (player) {
                        board.a2=1
                        setImg(1,a2)
                    }
                    else {
                        board.a2=2
                        setImg(2,a2)
                    }
                    checkForWin()
                }
        })
        a3.setOnClickListener(View.OnClickListener {

            if(board.active)
                if(a3.drawable == null) {
                    if (player) {
                        board.a3=1
                        setImg(1,a3)
                    }
                    else {
                        board.a3=2
                        setImg(2,a3)
                    }
                    checkForWin()
                }
        })

        b1.setOnClickListener(View.OnClickListener {

            if(board.active)
                if(b1.drawable == null) {
                    if (player) {
                        board.b1=1
                        setImg(1,b1)
                    }
                    else {
                        board.b1=2
                        setImg(2,b1)
                    }
                    checkForWin()
                }
        })
        b2.setOnClickListener(View.OnClickListener {

            if(board.active)
                if(b2.drawable == null) {
                    if (player) {
                        board.b2=1
                        setImg(1,b2)
                    }
                    else {
                        board.b2=2
                        setImg(2,b2)
                    }
                    checkForWin()
                }
        })
        b3.setOnClickListener(View.OnClickListener {

            if(board.active)
                if(b3.drawable == null) {
                    if (player) {
                        board.b3=1
                        setImg(1,b3)
                    }
                    else {
                        board.b3=2
                        setImg(2,b3)
                    }
                    checkForWin()
                }
        })

        c1.setOnClickListener(View.OnClickListener {

            if(board.active)
                if(c1.drawable == null) {
                    if (player) {
                        board.c1=1
                        setImg(1,c1)
                    }
                    else {
                        board.c1=2
                        setImg(2,c1)
                    }
                    checkForWin()
                }
        })
        c2.setOnClickListener(View.OnClickListener {

            if(board.active)
                if(c2.drawable == null) {
                    if (player) {
                        board.c2=1
                        setImg(1,c2)
                    }
                    else {
                        board.c2=2
                        setImg(2,c2)
                    }
                    checkForWin()
                }
        })
        c3.setOnClickListener(View.OnClickListener {

            if(board.active)
                if(c3.drawable == null) {
                    if (player) {
                        board.c3=1
                        setImg(1,c3)
                    }
                    else {
                        board.c3=2
                        setImg(2,c3)
                    }
                    checkForWin()
                }
        })

        newGame.setOnClickListener{

            boardViewModel.restartBoard()

            a1.setImageDrawable(null);a2.setImageDrawable(null);a3.setImageDrawable(null);
            b1.setImageDrawable(null);b2.setImageDrawable(null);b3.setImageDrawable(null);
            c1.setImageDrawable(null);c2.setImageDrawable(null);c3.setImageDrawable(null);

            player1.turn=!player1.turn
            player2.turn=!player2.turn

            player=true
            if(turn==1){
                turn = 0
            } else turn = 1

            changeColorNewGame()

        }

        resetScore.setOnClickListener{

            if(playerViewModel.getPlayer1().score!=0 || playerViewModel.getPlayer2().score!=0){

                val builder = AlertDialog.Builder(context)
                builder.setMessage("Are you sure you want to reset the score?")
                    .setCancelable(false)
                    .setPositiveButton("Yes") { dialog, id ->
                        player1.score=0
                        player2.score=0
                        score1.text="0"
                        score2.text="0"
                        GlobalScope.launch(Dispatchers.Main) {
                            playerViewModel.updatePlayer(player1)
                            playerViewModel.updatePlayer(player2)
                        }
                    }
                    .setNegativeButton("No") { dialog, id ->
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()

            }
        }

        return view

    }

    private fun changeColorNewGame() {

        if(turn==1) {
            name1.setTextColor(plava)
            name2.setTextColor(score1.currentTextColor)
        }
        else{
            name2.setTextColor(plava)
            name1.setTextColor(score2.currentTextColor)
        }
    }

    private fun changeColorPlay() {

        if(name1.getCurrentTextColor().toString() != "-15093050" ) {
            name1.setTextColor(plava)
            name2.setTextColor(score1.currentTextColor)
        }
        else{
            name2.setTextColor(plava)
            name1.setTextColor(score2.currentTextColor)
        }
    }



    private fun checkForWin(){

        player = !player

        if(board.a1==1 && board.a1==board.a2 && board.a1==board.a3){
            refrehScore(1);
            setImgW("iksW",a1);setImgW("iksW",a2);setImgW("iksW",a3);
        }
        else if(board.b1==1 && board.b1==board.b2 && board.b1==board.b3){
            refrehScore(1);
            setImgW("iksW",b1);setImgW("iksW",b2);setImgW("iksW",b3);
        }
        else if(board.c1==1 && board.c1==board.c2 && board.c1==board.c3){
            refrehScore(1);
            setImgW("iksW",c1);setImgW("iksW",c2);setImgW("iksW",c3);
        }
        else if(board.a1==1 && board.a1==board.b2 && board.a1==board.c3){
            refrehScore(1);
            setImgW("iksW",a1);setImgW("iksW",b2);setImgW("iksW",c3);
        }
        if(board.a3==1 && board.a3==board.b2 && board.a3==board.c1){
            refrehScore(1);
            setImgW("iksW",a3);setImgW("iksW",b2);setImgW("iksW",c1);
        }
        else if(board.a1==1 && board.a1==board.b1 && board.a1==board.c1){
            refrehScore(1);
            setImgW("iksW",a1);setImgW("iksW",b1);setImgW("iksW",c1);
        }
        else if(board.a2==1 && board.a2==board.b2 && board.a2==board.c2){
            refrehScore(1);
            setImgW("iksW",a2);setImgW("iksW",b2);setImgW("iksW",c2);
        }
        else if(board.a3==1 && board.a3==board.b3 && board.a3==board.c3){
            refrehScore(1);
            setImgW("iksW",a3);setImgW("iksW",b3);setImgW("iksW",c3);
        }
        else if(board.a1==2 && board.a1==board.a2 && board.a1==board.a3){
            refrehScore(2);
            setImgW("oksW",a1);setImgW("oksW",a2);setImgW("oksW",a3);
        }
        else if(board.b1==2 && board.b1==board.b2 && board.b1==board.b3){
            refrehScore(2);
            setImgW("oksW",b1);setImgW("oksW",b2);setImgW("oksW",b3);
        }
        else if(board.c1==2 && board.c1==board.c2 && board.c1==board.c3){
            refrehScore(2);
            setImgW("oksW",c1);setImgW("oksW",c2);setImgW("oksW",c3);
        }
        else if(board.a1==2 && board.a1==board.b2 && board.a1==board.c3){
            refrehScore(2);
            setImgW("oksW",a1);setImgW("oksW",b2);setImgW("oksW",c3);
        }
        else if(board.a3==2 && board.a3==board.b2 && board.a3==board.c1){
            refrehScore(2);
            setImgW("oksW",a3);setImgW("oksW",b2);setImgW("oksW",c1);
        }
        else if(board.a1==2 && board.a1==board.b1 && board.a1==board.c1){
            refrehScore(2);
            setImgW("oksW",a1);setImgW("oksW",b1);setImgW("oksW",c1);
        }
        else if(board.a2==2 && board.a2==board.b2 && board.a2==board.c2){
            refrehScore(2);
            setImgW("oksW",a2);setImgW("oksW",b2);setImgW("oksW",c2);
        }
        else if(board.a3==2 && board.a3==board.b3 && board.a3==board.c3){
            refrehScore(2);
            setImgW("oksW",a3);setImgW("oksW",b3);setImgW("oksW",c3);
        }
        else changeColorPlay()

    }

    private fun checkForWinOnResume(){

        if(board.a1==1 && board.a1==board.a2 && board.a1==board.a3){
            setImgW("iksW",a1);setImgW("iksW",a2);setImgW("iksW",a3);
        }
        else if(board.b1==1 && board.b1==board.b2 && board.b1==board.b3){
            setImgW("iksW",b1);setImgW("iksW",b2);setImgW("iksW",b3);
        }
        else if(board.c1==1 && board.c1==board.c2 && board.c1==board.c3){
            setImgW("iksW",c1);setImgW("iksW",c2);setImgW("iksW",c3);
        }
        else if(board.a1==1 && board.a1==board.b2 && board.a1==board.c3){
            setImgW("iksW",a1);setImgW("iksW",b2);setImgW("iksW",c3);
        }
        if(board.a3==1 && board.a3==board.b2 && board.a3==board.c1){
            setImgW("iksW",a3);setImgW("iksW",b2);setImgW("iksW",c1);
        }
        else if(board.a1==1 && board.a1==board.b1 && board.a1==board.c1){
            setImgW("iksW",a1);setImgW("iksW",b1);setImgW("iksW",c1);
        }
        else if(board.a2==1 && board.a2==board.b2 && board.a2==board.c2){
            setImgW("iksW",a2);setImgW("iksW",b2);setImgW("iksW",c2);
        }
        else if(board.a3==1 && board.a3==board.b3 && board.a3==board.c3){
            setImgW("iksW",a3);setImgW("iksW",b3);setImgW("iksW",c3);
        }
        else if(board.a1==2 && board.a1==board.a2 && board.a1==board.a3){
            setImgW("oksW",a1);setImgW("oksW",a2);setImgW("oksW",a3);
        }
        else if(board.b1==2 && board.b1==board.b2 && board.b1==board.b3){
            setImgW("oksW",b1);setImgW("oksW",b2);setImgW("oksW",b3);
        }
        else if(board.c1==2 && board.c1==board.c2 && board.c1==board.c3){
            setImgW("oksW",c1);setImgW("oksW",c2);setImgW("oksW",c3);
        }
        else if(board.a1==2 && board.a1==board.b2 && board.a1==board.c3){
            setImgW("oksW",a1);setImgW("oksW",b2);setImgW("oksW",c3);
        }
        else if(board.a3==2 && board.a3==board.b2 && board.a3==board.c1){
            setImgW("oksW",a3);setImgW("oksW",b2);setImgW("oksW",c1);
        }
        else if(board.a1==2 && board.a1==board.b1 && board.a1==board.c1){
            setImgW("oksW",a1);setImgW("oksW",b1);setImgW("oksW",c1);
        }
        else if(board.a2==2 && board.a2==board.b2 && board.a2==board.c2){
            setImgW("oksW",a2);setImgW("oksW",b2);setImgW("oksW",c2);
        }
        else if(board.a3==2 && board.a3==board.b3 && board.a3==board.c3){
            setImgW("oksW",a3);setImgW("oksW",b3);setImgW("oksW",c3);
        }

    }

    private fun setImg(turn: Int, imageView: ImageView){

        if(player1.theme=="Classic") {
            if (turn == 1) {
                imageView.setImageResource(R.drawable.xclassic)
            }
            else if (turn == 2){
                imageView.setImageResource(R.drawable.oclassic)
            }
        }
        else if(player1.theme=="Halloween") {
            if (turn == 1) {
                imageView.setImageResource(R.drawable.skull)
            }
            else if (turn == 2){
                imageView.setImageResource(R.drawable.pumpkin)
            }
        }
        else if(player1.theme=="Cartoon") {
            if (turn == 1) {
                imageView.setImageResource(R.drawable.xtoon)
            }
            else if (turn == 2){
                imageView.setImageResource(R.drawable.otoon)
            }
        }
        else if(player1.theme=="Derbi") {
            if (turn == 1) {
                imageView.setImageResource(R.drawable.sar)
            }
            else if (turn == 2){
                imageView.setImageResource(R.drawable.zelj)
            }
        }
    }

    private fun setImgW(sign: String, imageView: ImageView){

        if(player1.theme=="Classic") {
            if (sign == "oksW") {
                imageView.setImageResource(R.drawable.oclassicr)
            } else if (sign == "iksW") {
                imageView.setImageResource(R.drawable.xclassicr)
            }
        }
        else if(player1.theme=="Halloween") {
            if (sign == "oksW") {
                imageView.setImageResource(R.drawable.pumpkinw)
            } else if (sign == "iksW") {
                imageView.setImageResource(R.drawable.skullw)
            }
        }
        else if(player1.theme=="Cartoon") {
            if (sign == "oksW") {
                imageView.setImageResource(R.drawable.otoonw)
            } else if (sign == "iksW") {
                imageView.setImageResource(R.drawable.xtoonw)
            }
        }
        else if(player1.theme=="Derbi") {
            if (sign == "oksW") {
                imageView.setImageResource(R.drawable.zeljw)
            } else if (sign == "iksW") {
                imageView.setImageResource(R.drawable.sarw)
            }
        }

    }

    private fun refrehScore(plt : Int){

        board.active=false

        if(plt==1) {
            if (turn==1) {
                player1.score++
            } else {
                player2.score++
            }
        }
        else if(plt==2) {
            if (turn==0) {
                player1.score++
            } else {
                player2.score++
            }
        }

        GlobalScope.launch(Dispatchers.Main) {
            playerViewModel.updatePlayer(player1)
            playerViewModel.updatePlayer(player2)
        }

        playerViewModel.setPlayer1()
        playerViewModel.setPlayer2()

        score1.text=player1.score.toString()
        score2.text=player2.score.toString()

    }

    companion object {
        fun newInstance(adapter: ViewPagerAdapter) =
            OfflineGameFragment().apply {
                this.pagerAdapter = adapter
            }
    }

    override fun onResume() {
        super.onResume()

        if(boardViewModel.isEmpty()){
            a1.setImageDrawable(null);a2.setImageDrawable(null);a3.setImageDrawable(null);
            b1.setImageDrawable(null);b2.setImageDrawable(null);b3.setImageDrawable(null);
            c1.setImageDrawable(null);c2.setImageDrawable(null);c3.setImageDrawable(null);
        }

        player1 = playerViewModel.getPlayer1()
        name1.text=player1.name+":"

        player2 = playerViewModel.getPlayer2()
        name2.text=player2.name+":"

        score1.text=player1.score.toString()
        score2.text=player2.score.toString()

        if(board.a1!=0){
            setImg(board.a1,a1)
        }
        if(board.a2!=0){
            setImg(board.a2,a2)
        }
        if(board.a3!=0){
            setImg(board.a3,a3)
        }

        if(board.b1!=0){
            setImg(board.b1,b1)
        }
        if(board.b2!=0){
            setImg(board.b2,b2)
        }
        if(board.b3!=0){
            setImg(board.b3,b3)
        }

        if(board.c1!=0){
            setImg(board.c1,c1)
        }
        if(board.c2!=0){
            setImg(board.c2,c2)
        }
        if(board.c3!=0){
            setImg(board.c3,c3)
        }

        checkForWinOnResume()

    }

}
