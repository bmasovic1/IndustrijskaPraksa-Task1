package com.hulkapps.tictactoe.view

import android.os.Bundle
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


class FirstFragment : Fragment() {


    private lateinit var pl: ImageButton;
    private lateinit var ai: ImageButton;
    private lateinit var pagerAdapter: ViewPagerAdapter
    var playerViewModel = PlayerViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_first, container, false)

        pl = view.findViewById(R.id.plVsPl)
        ai = view.findViewById(R.id.vsAi)

        playerViewModel.setContext(requireContext())

        playerViewModel.setPlayer1()
        playerViewModel.setPlayer2()
        playerViewModel.setPlayerAi()

        try{
            pagerAdapter.remove(2)
        }
        catch (e:Exception){}
        try{
            pagerAdapter.remove(1)
        }
        catch (e:Exception){}

        pl.setOnClickListener{

            pagerAdapter.change(0, PvPModeFragment.newInstance(pagerAdapter))

        }

        ai.setOnClickListener{

            pagerAdapter.change(0, GameVsAiFragment.newInstance(pagerAdapter))
            pagerAdapter.add(1, SettingsVsAiFragment.newInstance(pagerAdapter))

        }

        return view

    }

    companion object {
        fun newInstance(adapter: ViewPagerAdapter) =
            FirstFragment().apply {
                this.pagerAdapter = adapter
            }
    }

    override fun onResume() {
        super.onResume()

    }

}


