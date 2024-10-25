package com.hulkapps.tictactoe.view

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import com.google.firebase.database.*
import com.hulkapps.tictactoe.R
import com.hulkapps.tictactoe.data.repositories.BoardRepository
import com.hulkapps.tictactoe.viewmodel.BoardViewModel
import com.hulkapps.tictactoe.viewmodel.PlayerViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SettingsVsAiFragment : Fragment() {

    private lateinit var pagerAdapter: ViewPagerAdapter
    private lateinit var spinner: Spinner;
    private lateinit var themePreview: ImageView;
    private lateinit var themePreview2: ImageView;
    private lateinit var saveSettings: Button;
    private lateinit var namePLayer1: EditText;
    private var firebaseDatabase: FirebaseDatabase? = null
    private var databaseReference: DatabaseReference? = null
    var boardViewModel = BoardViewModel()
    var playerViewModel = PlayerViewModel()
    var player1 = playerViewModel.getPlayer1()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_settings_vs_ai, container, false)

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                val check : Boolean = "GameVsAiFragment" in pagerAdapter.clear()

                if(check) {
                    pagerAdapter.remove(1)
                    pagerAdapter.add(0, FirstFragment.newInstance(pagerAdapter))
                }
                else{
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

                        try{

                            pagerAdapter.remove(1)

                            if(!check) {
                                pagerAdapter.add(0, CreateJoinFragment.newInstance(pagerAdapter))
                            }
                            else {
                                pagerAdapter.add(0, FirstFragment.newInstance(pagerAdapter))
                            }

                        }
                        catch (e: java.lang.Exception){}



                    }
                    .setNegativeButton("No") { dialog, id ->
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()

                }

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

                if(snapshot.key=="close") {

                    try{
                        pagerAdapter.remove(1)
                        pagerAdapter.clear()
                        pagerAdapter.add(0, CreateJoinFragment.newInstance(pagerAdapter))
                    }
                    catch (e: java.lang.Exception){}

                }

            }

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

        })


        spinner = view.findViewById(R.id.themeSelector)
        themePreview = view.findViewById(R.id.themePreview)
        themePreview2 = view.findViewById(R.id.themePreview2)
        saveSettings = view.findViewById(R.id.saveSettings)
        namePLayer1 = view.findViewById(R.id.namePLayer1)

        namePLayer1.setText(playerViewModel.getPlayer1().name)

        spinner.adapter = ArrayAdapter<String>(

            requireActivity(),
            android.R.layout.simple_list_item_1,
            listOf("Classic", "Halloween", "Cartoon", "Derbi")

        );


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                when (position) {

                    0 -> {themePreview.setImageResource(R.drawable.xclassic); themePreview2.setImageResource(R.drawable.oclassic)}
                    1 -> {themePreview.setImageResource(R.drawable.skull); themePreview2.setImageResource(R.drawable.pumpkin)}
                    2 -> {themePreview.setImageResource(R.drawable.xtoonprew); themePreview2.setImageResource(R.drawable.otoonprew)}
                    3 -> {themePreview.setImageResource(R.drawable.sar); themePreview2.setImageResource(R.drawable.zelj)}

                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        if(player1.theme=="Classic") {
            spinner.setSelection(0)
            themePreview.setImageResource(R.drawable.xclassic); themePreview2.setImageResource(R.drawable.oclassic)
        }
        else if(player1.theme=="Halloween") {
            spinner.setSelection(1)
            themePreview.setImageResource(R.drawable.skull); themePreview2.setImageResource(R.drawable.pumpkin)
        }
        else if(player1.theme=="Cartoon") {
            spinner.setSelection(2)
            themePreview.setImageResource(R.drawable.xtoonprew); themePreview2.setImageResource(R.drawable.otoonprew)
        }
        else if(player1.theme=="Derbi") {
            spinner.setSelection(3)
            themePreview.setImageResource(R.drawable.sar); themePreview2.setImageResource(R.drawable.zelj)
        }

        saveSettings.setOnClickListener{

            playerViewModel.setPlayer1Name(namePLayer1.text.toString())

            GlobalScope.launch(Dispatchers.Main) {
                player1.name = namePLayer1.text.toString()
                player1.theme = spinner.selectedItem.toString()
                playerViewModel.updatePlayer(player1)
            }


            Toast.makeText(context, "Data saved", Toast.LENGTH_LONG).show()


            val check : Boolean = "GameVsAiFragment" in pagerAdapter.clear()

            if(!check) {

                if(BoardRepository.creator){

                    val bo = mapOf<String,Any>(
                        "player1" to namePLayer1.text.toString(),
                    )

                    databaseReference!!.updateChildren(bo)
                        .addOnSuccessListener {
                        }
                        .addOnFailureListener { ex : Exception ->
                        }

                }
                else{

                    val bo = mapOf<String,Any>(
                        "player2" to namePLayer1.text.toString(),
                    )

                    databaseReference!!.updateChildren(bo)
                        .addOnSuccessListener {
                        }
                        .addOnFailureListener { ex : Exception ->
                        }

                }

            }


        }

        return view

    }

    companion object {
        fun newInstance(adapter: ViewPagerAdapter) =
            SettingsVsAiFragment().apply {
                this.pagerAdapter = adapter
            }
    }

}


