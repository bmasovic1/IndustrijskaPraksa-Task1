package com.hulkapps.tictactoe.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import com.hulkapps.tictactoe.R
import com.hulkapps.tictactoe.viewmodel.BoardViewModel
import com.hulkapps.tictactoe.viewmodel.PlayerViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SettingsFragment : Fragment() {

    private lateinit var pagerAdapter: ViewPagerAdapter
    private lateinit var spinner: Spinner;
    private lateinit var themePreview: ImageView;
    private lateinit var themePreview2: ImageView;
    private lateinit var saveSettings: Button;
    private lateinit var namePLayer1: EditText;
    private lateinit var namePLayer2: EditText;
    var boardViewModel = BoardViewModel()
    var playerViewModel = PlayerViewModel()

    var player1 = playerViewModel.getPlayer1()
    var player2 = playerViewModel.getPlayer2()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                try{
                    pagerAdapter.remove(1)
                    pagerAdapter.add(0, PvPModeFragment.newInstance(pagerAdapter))
                }
                catch (e: java.lang.Exception){}

            }
        })

        spinner = view.findViewById(R.id.themeSelector)
        themePreview = view.findViewById(R.id.themePreview)
        themePreview2 = view.findViewById(R.id.themePreview2)
        saveSettings = view.findViewById(R.id.saveSettings)
        namePLayer1 = view.findViewById(R.id.namePLayer1)
        namePLayer2 = view.findViewById(R.id.namePLayer2)

        namePLayer1.setText(playerViewModel.getPlayer1().name)
        namePLayer2.setText(playerViewModel.getPlayer2().name)

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
                player2.name = namePLayer2.text.toString()
                playerViewModel.updatePlayer(player1)
                playerViewModel.updatePlayer(player2)
            }

            boardViewModel.restartBoardAi()

            Toast.makeText(context, "Data saved", Toast.LENGTH_LONG).show()

        }


        return view

    }

    companion object {
        fun newInstance(adapter: ViewPagerAdapter) =
            SettingsFragment().apply {
                this.pagerAdapter = adapter
            }
    }

}


