package com.hulkapps.tictactoe.view

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.hulkapps.tictactoe.R
import com.hulkapps.tictactoe.data.models.Player
import com.hulkapps.tictactoe.viewmodel.PlayerViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception


class WelcomeFragment : Fragment() {


    private lateinit var play: Button;
    private lateinit var username: EditText;
    private lateinit var pagerAdapter: ViewPagerAdapter
    var playerViewModel = PlayerViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_welcome, container, false)

        play = view.findViewById(R.id.play)
        username = view.findViewById(R.id.userName)

        playerViewModel.setContext(requireContext())

        playerViewModel.setPlayer1()
        playerViewModel.setPlayer2()
        playerViewModel.setPlayerAi()

        try{
            pagerAdapter.remove(1)
        }
        catch (e:Exception){}

        play.setOnClickListener{

            val player = Player(null, username.text.toString(), 0, 0,"Classic",true)
            val player2 = Player(null,"Player2", 0, 0,"Classic",true)
            val player3 = Player(null,"Player3", 0, 0,"Classic",true)

            GlobalScope.launch (Dispatchers.Main){
                playerViewModel.addPlayer(player)
                playerViewModel.addPlayer(player2)
                playerViewModel.addPlayer(player3)
            }


            playerViewModel.setPlayer1()
            playerViewModel.setPlayer2()
            playerViewModel.setPlayerAi()

            Handler().postDelayed({

            pagerAdapter.change(0, FirstFragment.newInstance(pagerAdapter))

            }, 500)
        }



        return view

    }

    companion object {
        fun newInstance(adapter: ViewPagerAdapter) =
            WelcomeFragment().apply {
                this.pagerAdapter = adapter
            }
    }

}


