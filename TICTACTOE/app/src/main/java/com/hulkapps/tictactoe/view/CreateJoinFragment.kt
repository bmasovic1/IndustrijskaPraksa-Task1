package com.hulkapps.tictactoe.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.hulkapps.tictactoe.R
import com.hulkapps.tictactoe.data.models.Board
import com.hulkapps.tictactoe.data.repositories.BoardRepository
import com.hulkapps.tictactoe.viewmodel.BoardViewModel
import com.hulkapps.tictactoe.viewmodel.PlayerViewModel


class CreateJoinFragment : Fragment() {


    private lateinit var create: Button;
    private lateinit var join: Button;
    private lateinit var pagerAdapter: ViewPagerAdapter
    private var firebaseDatabase: FirebaseDatabase? = null
    private var databaseReference: DatabaseReference? = null
    private lateinit var code: TextView;
    var playerViewModel = PlayerViewModel()
    var boardViewModel = BoardViewModel()

    var player1 = playerViewModel.getPlayer1()
    var board = boardViewModel.getBoard()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_create_join, container, false)

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                try{
                    pagerAdapter.change(0, PvPModeFragment.newInstance(pagerAdapter))
                }
                catch (e: java.lang.Exception){}

            }
        })

        create = view.findViewById(R.id.create)
        join = view.findViewById(R.id.join)
        code = view.findViewById(R.id.code)

        boardViewModel.restartPvPBoard()

        try{
            pagerAdapter.remove(1)
        }
        catch (e: java.lang.Exception){}

        firebaseDatabase = FirebaseDatabase.getInstance("https://tictactoe-365ea-default-rtdb.europe-west1.firebasedatabase.app/")
        databaseReference = firebaseDatabase!!.reference.child("Boards")

        create.setOnClickListener{

            if(!isOnline(requireContext())){
                Toast.makeText(context, "Please connect to the internet!", Toast.LENGTH_LONG).show()
            }

            databaseReference!!.child(code.text.toString()).get()
                .addOnSuccessListener {

                    if(code.text.toString()==""){
                        Toast.makeText(context, "Please enter room code!", Toast.LENGTH_LONG).show()
                    }
                    else if(it.exists()) {
                        Toast.makeText(context, "Room code already in use, please try another one.", Toast.LENGTH_LONG).show()
                    }
                    else{
                        BoardRepository.pl1=true
                        BoardRepository.creator=true
                        BoardRepository.boardActive()
                        BoardRepository.room=code.text.toString()
                        databaseReference!!.child(code.text.toString()).setValue(Board(0,0,0,0,0,0,0,0,0,false,0,true,true,player1.name,"",0,0,0,0))
                        pagerAdapter.change(0, GameFragment.newInstance(pagerAdapter))
                        pagerAdapter.add(1, SettingsVsAiFragment.newInstance(pagerAdapter))
                    }

                }
                .addOnFailureListener { ex : Exception ->
                }



        }

        join.setOnClickListener{

            databaseReference!!.child(code.text.toString()).get()
                .addOnSuccessListener {

                    if(it.exists()) {

                        val bo = mapOf<String,Any>(
                            "active" to true,
                            "player2" to player1.name,
                        )

                        databaseReference!!.child(code.text.toString()).updateChildren(bo)
                            .addOnSuccessListener {
                            }
                            .addOnFailureListener { ex : Exception ->
                            }


                        BoardRepository.pl1=false
                        BoardRepository.creator=false
                        BoardRepository.room=code.text.toString()
                        pagerAdapter.change(0, GameFragment.newInstance(pagerAdapter))
                        pagerAdapter.add(1, SettingsVsAiFragment.newInstance(pagerAdapter))
                    }
                    else
                        Toast.makeText(context, "Room doesnt exist", Toast.LENGTH_LONG).show()


                }
                .addOnFailureListener { ex : Exception ->
                }

        }

        return view

    }

    private fun isOnline(context: Context): Boolean {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

    companion object {
        fun newInstance(adapter: ViewPagerAdapter) =
            CreateJoinFragment().apply {
                this.pagerAdapter = adapter
            }
    }

}