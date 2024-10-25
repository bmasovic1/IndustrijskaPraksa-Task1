package com.hulkapps.tictactoe.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import com.hulkapps.tictactoe.R
import com.hulkapps.tictactoe.data.repositories.BoardRepository
import com.hulkapps.tictactoe.viewmodel.BoardViewModel
import com.hulkapps.tictactoe.viewmodel.PlayerViewModel


class GameFragment : Fragment() {

    private lateinit var pagerAdapter: ViewPagerAdapter
    var player: Boolean = true
    var turn: Int = 1
    var pla1: Boolean = false
    var pl1Score: Int = 0
    var pl2Score: Int = 0
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
    private var firebaseDatabase: FirebaseDatabase? = null
    private var databaseReference: DatabaseReference? = null
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
                val builder = AlertDialog.Builder(context)
                builder.setMessage("Are you sure you want to leave the room")
                    .setCancelable(false)
                    .setPositiveButton("Yes") { dialog, id ->

                        boardViewModel.restartBoard()

                        val resN : Int = 2

                        val bo = mapOf<String,Any>(
                            "close" to resN,
                        )

                        databaseReference!!.updateChildren(bo)
                            .addOnSuccessListener {
                            }
                            .addOnFailureListener { ex : Exception ->
                            }

                        databaseReference!!.removeValue()

                        try{
                            pagerAdapter.remove(1)
                            pagerAdapter.clear()
                            pagerAdapter.add(0, CreateJoinFragment.newInstance(pagerAdapter))
                        }
                        catch (e: java.lang.Exception){}



                    }
                    .setNegativeButton("No") { dialog, id ->
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()

            }
        })


        firebaseDatabase = FirebaseDatabase.getInstance("https://tictactoe-365ea-default-rtdb.europe-west1.firebasedatabase.app/")
        databaseReference = firebaseDatabase!!.reference.child("Boards").child(BoardRepository.room)

        databaseReference!!.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

                var data = snapshot.value

                if(snapshot.key=="resetScore1") {

                    board.resetScore1 = data.toString().toInt()
                    if(board.resetScore1==1 && board.resetScore2==1){


                        val resN : Int = 0

                        score1.text="0"
                        score2.text="0"
                        pl1Score=0
                        pl2Score=0

                        val bo = mapOf<String,Any>(
                            "resetScore1" to resN,
                            "resetScore2" to resN
                        )

                        databaseReference!!.updateChildren(bo)
                            .addOnSuccessListener {
                            }
                            .addOnFailureListener { ex : Exception ->
                            }
                    }
                    else if(board.resetScore1==1 && board.resetScore2==0){
                        resetScore.setBackgroundColor(Color.GREEN)
                    }
                    else if(board.resetScore1==0 && board.resetScore2==0){
                        resetScore.setBackgroundColor(0)
                    }

                }


                if(snapshot.key=="resetScore2") {

                    board.resetScore2 = data.toString().toInt()
                    if(board.resetScore1==1 && board.resetScore2==1){

                        score1.text="0"
                        score2.text="0"

                        val resN : Int = 0

                        val bo = mapOf<String,Any>(
                            "resetScore1" to resN,
                            "resetScore2" to resN
                        )

                        databaseReference!!.updateChildren(bo)
                            .addOnSuccessListener {
                            }
                            .addOnFailureListener { ex : Exception ->
                            }
                    }
                    else if(board.resetScore2==1 && board.resetScore1==0){
                        resetScore.setBackgroundColor(Color.GREEN)
                    }
                    else if(board.resetScore1==0 && board.resetScore2==0){
                        resetScore.setBackgroundColor(0)
                    }

                }


                if(snapshot.key=="newGame1") {

                    board.newGame1 = data.toString().toInt()
                    if(board.newGame1==1 && board.newGame2==1){

                        changeColorPlay()

                        val resN : Int = 0
                        val tr : Boolean = true
                        var nG1 : Int = 0
                        var nG2 : Int = 0

                        val bo = mapOf<String,Any>(
                            "a1" to resN,
                            "a2" to resN,
                            "a3" to resN,
                            "b1" to resN,
                            "b2" to resN,
                            "b3" to resN,
                            "c1" to resN,
                            "c2" to resN,
                            "c3" to resN,
                            "pl1" to tr,
                            "turn" to !board.turn,
                            "newGame1" to resN,
                            "newGame2" to resN
                        )

                        databaseReference!!.updateChildren(bo)
                            .addOnSuccessListener {
                            }
                            .addOnFailureListener { ex : Exception ->
                            }
                    }
                    else if(board.newGame1==1 && board.newGame2==0){
                        newGame.setBackgroundColor(Color.GREEN)
                    }
                    else if(board.newGame1==0 && board.newGame2==0){
                        newGame.setBackgroundColor(plava)
                    }

                }

                if(snapshot.key=="newGame2") {

                    board.newGame2 = data.toString().toInt()
                    if(board.newGame1==1 && board.newGame2==1){

                        changeColorPlay()

                        val resN : Int = 0
                        val tr : Boolean = true
                        var nG1 : Int = 0
                        var nG2 : Int = 0

                        val bo = mapOf<String,Any>(
                            "a1" to resN,
                            "a2" to resN,
                            "a3" to resN,
                            "b1" to resN,
                            "b2" to resN,
                            "b3" to resN,
                            "c1" to resN,
                            "c2" to resN,
                            "c3" to resN,
                            "pl1" to tr,
                            "turn" to !board.turn,
                            "newGame1" to resN,
                            "newGame2" to resN
                        )

                        databaseReference!!.updateChildren(bo)
                            .addOnSuccessListener {
                            }
                            .addOnFailureListener { ex : Exception ->
                            }
                    }
                    else if(board.newGame2==1 && board.newGame1==0){
                        newGame.setBackgroundColor(Color.GREEN)
                    }
                    else if(board.newGame1==0 && board.newGame2==0){
                        newGame.setBackgroundColor(plava)
                    }

                }

                if(snapshot.key=="active") {

                    board.active = data.toString().toBoolean()

                }

                if(snapshot.key=="close") {


                    try{
                        pagerAdapter.remove(1)
                        pagerAdapter.clear()
                        pagerAdapter.add(0, CreateJoinFragment.newInstance(pagerAdapter))
                    }
                    catch (e: java.lang.Exception){}

                }

                if(snapshot.key=="player2") {

                    if(BoardRepository.creator){
                        name2.text=snapshot.value as String+":"
                    }
                    else {
                        name1.text=snapshot.value as String+":"
                    }

                }

                if(snapshot.key=="player1") {

                    if(BoardRepository.creator){
                        name1.text=snapshot.value as String+":"
                    }
                    else {
                        name2.text=snapshot.value as String+":"
                    }

                }

                if(snapshot.key=="turn" ){

                    pla1 = !pla1
                    board.turn=!board.turn
                    player = true
                    boardViewModel.restartBoard()

                }
                if(snapshot.key=="pl1" ){

                    board.pl1= snapshot.value as Boolean
                    changeColorPlay()

                }

                if(snapshot.key=="a1" && board.a1 != data.toString().toInt() ){
                    val daaaa = data.toString().toInt()
                    if(daaaa==0){
                        board.a1=data.toString().toInt()
                        a1.setImageDrawable(null)
                    }
                    else {
                        player = !player
                        board.a1 = daaaa
                        play(1, daaaa)
                        setImg(daaaa, a1)
                        checkForWin()
                    }
                }
                if(snapshot.key=="a2" && board.a2 != data.toString().toInt()){
                    val daaaa = data.toString().toInt()
                    if(daaaa==0){
                        board.a2=data.toString().toInt()
                        a2.setImageDrawable(null)
                    }
                    else {
                        player = !player
                        board.a2 = daaaa
                        play(2, daaaa)
                        setImg(daaaa, a2)
                        checkForWin()
                    }
                }
                if(snapshot.key=="a3" && board.a3 != data.toString().toInt()){
                    val daaaa = data.toString().toInt()
                    if(daaaa==0){
                        board.a3=data.toString().toInt()
                        a3.setImageDrawable(null)
                    }
                    else {
                        player = !player
                        board.a3 = daaaa
                        play(3, daaaa)
                        setImg(daaaa, a3)
                        checkForWin()
                    }
                }
                if(snapshot.key=="b1" && board.b1 != data.toString().toInt()){
                    val daaaa = data.toString().toInt()
                    if(daaaa==0){
                        board.b1=data.toString().toInt()
                        b1.setImageDrawable(null)
                    }
                    else {
                        player = !player
                        board.b1 = daaaa
                        play(4, daaaa)
                        setImg(daaaa, b1)
                        b1.performClick()
                        checkForWin()
                    }
                }
                if(snapshot.key=="b2" && board.b2 != data.toString().toInt()){
                    val daaaa = data.toString().toInt()
                    if(daaaa==0){
                        board.b2=data.toString().toInt()
                        b2.setImageDrawable(null)
                    }
                    else {
                        player = !player
                        board.b2 = daaaa
                        play(5, daaaa)
                        setImg(daaaa, b2)
                        checkForWin()
                    }
                }
                if(snapshot.key=="b3" && board.b3 != data.toString().toInt()){
                    val daaaa = data.toString().toInt()
                    if(daaaa==0){
                        board.b3=data.toString().toInt()
                        b3.setImageDrawable(null)
                    }
                    else {
                        player = !player
                        board.b3 = daaaa
                        play(6, daaaa)
                        setImg(daaaa, b3)
                        checkForWin()
                    }
                }
                if(snapshot.key=="c1" && board.c1 != data.toString().toInt()){
                    val daaaa = data.toString().toInt()
                    if(daaaa==0){
                        board.c1=data.toString().toInt()
                        c1.setImageDrawable(null)
                    }
                    else {
                        player = !player
                        board.c1 = daaaa
                        play(7, daaaa)
                        setImg(daaaa, c1)
                        checkForWin()
                    }
                }
                if(snapshot.key=="c2" && board.c2 != data.toString().toInt()){
                    val daaaa = data.toString().toInt()
                    if(daaaa==0){
                        board.c2=data.toString().toInt()
                        c2.setImageDrawable(null)
                    }
                    else {
                        player = !player
                        board.c2 = daaaa
                        play(8, daaaa)
                        setImg(daaaa, c2)
                        checkForWin()
                    }
                }
                if(snapshot.key=="c3" && board.c3 != data.toString().toInt()){
                    val daaaa = data.toString().toInt()
                    if(daaaa==0){
                        board.c3=data.toString().toInt()
                        c3.setImageDrawable(null)
                    }
                    else {
                        player = !player
                        board.c3 = daaaa
                        play(9, daaaa)
                        setImg(daaaa, c3)
                        checkForWin()
                    }
                }



            }
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
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

        pla1=BoardRepository.pl1


        if(pla1){
            name1.text=player1.name+":"
            name2.text="Player2:"
            name1.setTextColor(plava)
        }
        else {
            databaseReference!!.get()
                .addOnSuccessListener {
                    var name = it.value.toString().substringAfter("player1=").substringBefore(',')
                    name2.text=name+":"
                }
            name1.text = player1.name + ":"
            name2.setTextColor(plava)
        }

        score1.text="0"
        score2.text="0"



        a1.setOnClickListener(View.OnClickListener {

            if(board.active)
            if(board.pl1==pla1){
                if(a1.drawable == null) {
                    if (player) {
                        board.a1=1
                        play(1,1)
                        setImg(1,a1)
                    }
                    else {
                        board.a1=2
                        play(1,2)
                        setImg(2,a1)
                    }
                    player=!player
                    checkForWin()
                }
            }
        })
        a2.setOnClickListener(View.OnClickListener {

            if(board.active)
            if(board.pl1==pla1)
                if(a2.drawable == null) {
                    if (player) {
                        board.a2=1
                        play(2,1)
                        setImg(1,a2)
                    }
                    else {
                        board.a2=2
                        play(2,2)
                        setImg(2,a2)
                    }
                    player=!player
                    checkForWin()
                }
        })
        a3.setOnClickListener(View.OnClickListener {

            if(board.active)
            if(board.pl1==pla1)
                if(a3.drawable == null) {
                    if (player) {
                        board.a3=1
                        play(3,1)
                        setImg(1,a3)
                    }
                    else {
                        board.a3=2
                        play(3,2)
                        setImg(2,a3)
                    }
                    player=!player
                    checkForWin()
                }
        })

        b1.setOnClickListener(View.OnClickListener {

            if(board.active)
            if(board.pl1==pla1)
                if(b1.drawable == null) {
                    if (player) {
                        board.b1=1
                        play(4,1)
                        setImg(1,b1)
                    }
                    else {
                        board.b1=2
                        play(4,2)
                        setImg(2,b1)
                    }
                    player=!player
                    checkForWin()
                }
        })
        b2.setOnClickListener(View.OnClickListener {

            if(board.active)
            if(board.pl1==pla1)
                if(b2.drawable == null) {
                    if (player) {
                        board.b2=1
                        play(5,1)
                        setImg(1,b2)
                    }
                    else {
                        board.b2=2
                        play(5,2)
                        setImg(2,b2)
                    }
                    player=!player
                    checkForWin()
                }
        })
        b3.setOnClickListener(View.OnClickListener {

            if(board.active)
            if(board.pl1==pla1)
                if(b3.drawable == null) {
                    if (player) {
                        board.b3=1
                        play(6,1)
                        setImg(1,b3)
                    }
                    else {
                        board.b3=2
                        play(6,2)
                        setImg(2,b3)
                    }
                    player=!player
                    checkForWin()
                }
        })

        c1.setOnClickListener(View.OnClickListener {

            if(board.active)
            if(board.pl1==pla1)
                if(c1.drawable == null) {
                    if (player) {
                        board.c1=1
                        play(7,1)
                        setImg(1,c1)
                    }
                    else {
                        board.c1=2
                        play(7,2)
                        setImg(2,c1)
                    }
                    player=!player
                    checkForWin()
                }
        })
        c2.setOnClickListener(View.OnClickListener {

            if(board.active)
            if(board.pl1==pla1)
                if(c2.drawable == null) {
                    if (player) {
                        board.c2=1
                        play(8,1)
                        setImg(1,c2)
                    }
                    else {
                        board.c2=2
                        play(8,2)
                        setImg(2,c2)
                    }
                    player=!player
                    checkForWin()
                }
        })
        c3.setOnClickListener(View.OnClickListener {

            if(board.active)
            if(board.pl1==pla1)
                if(c3.drawable == null) {
                    if (player) {
                        board.c3=1
                        play(9,1)
                        setImg(1,c3)
                    }
                    else {
                        board.c3=2
                        play(9,2)
                        setImg(2,c3)
                    }
                    player=!player
                    checkForWin()
                }
        })

        newGame.setOnClickListener{

            var nG1 : Int = 0
            var nG2 : Int = 0

            if(BoardRepository.creator){
                if(board.newGame1==0){
                    nG1=1
                }
                else
                    nG1=0

                nG2=board.newGame2
            }
            else {
                if(board.newGame2==0){
                    nG2=1
                }
                else
                    nG2=0

                nG1=board.newGame1
            }

            val bo = mapOf<String,Any>(
                "newGame1" to nG1,
                "newGame2" to nG2
            )

            databaseReference!!.updateChildren(bo)
                .addOnSuccessListener {

                }
                .addOnFailureListener { ex : Exception ->
                }


        }

        resetScore.setOnClickListener{

            var nG1 : Int = 0
            var nG2 : Int = 0

            if(BoardRepository.creator){
                if(board.resetScore1==0){
                    nG1=1
                }
                else
                    nG1=0

                nG2=board.resetScore2
            }
            else {
                if(board.resetScore2==0){
                    nG2=1
                }
                else
                    nG2=0

                nG1=board.resetScore1
            }

            val bo = mapOf<String,Any>(
                "resetScore1" to nG1,
                "resetScore2" to nG2
            )

            databaseReference!!.updateChildren(bo)
                .addOnSuccessListener {
                }
                .addOnFailureListener { ex : Exception ->
                }

        }

        return view

    }

    private fun play(field : Int, play: Int) {

        if(field==1) board.a1=play
        if(field==2) board.a2=play
        if(field==3) board.a3=play
        if(field==4) board.b1=play
        if(field==5) board.b2=play
        if(field==6) board.b3=play
        if(field==7) board.c1=play
        if(field==8) board.c2=play
        if(field==9) board.c3=play

        board.pl1=!board.pl1

        val bo = mapOf<String,Any>(
            "a1" to board.a1,
            "a2" to board.a2,
            "a3" to board.a3,
            "b1" to board.b1,
            "b2" to board.b2,
            "b3" to board.b3,
            "c1" to board.c1,
            "c2" to board.c2,
            "c3" to board.c3,
            "active" to board.active,
            "pl1" to board.pl1
        )

        databaseReference!!.updateChildren(bo)
            .addOnSuccessListener {
            }
            .addOnFailureListener { ex : Exception ->
            }
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

        val bo = mapOf<String,Any>(
            "active" to  !board.active
        )

        databaseReference!!.updateChildren(bo)
            .addOnSuccessListener {
            }
            .addOnFailureListener { ex : Exception ->
            }

        if(plt==1) {
            if (pla1) {
                pl1Score++
            } else {
                pl2Score++
            }
        }
        else if(plt==2) {
            if (!pla1) {
                pl1Score++
            } else {
                pl2Score++
            }
        }

        score1.text=pl1Score.toString()
        score2.text=pl2Score.toString()

    }

    companion object {
        fun newInstance(adapter: ViewPagerAdapter) =
            GameFragment().apply {
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

    }



}
