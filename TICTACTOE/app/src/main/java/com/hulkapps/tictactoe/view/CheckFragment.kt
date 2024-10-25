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


class CheckFragment : Fragment() {



    private lateinit var pagerAdapter: ViewPagerAdapter
    var playerViewModel = PlayerViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_check, container, false)

        playerViewModel.setContext(requireContext())

        GlobalScope.launch (Dispatchers.Main){


           if(playerViewModel.getCount()!=0)
                pagerAdapter.change(0, FirstFragment.newInstance(pagerAdapter))
            else
                pagerAdapter.change(0, WelcomeFragment.newInstance(pagerAdapter))

        }

        return view

    }

    companion object {
        fun newInstance(adapter: ViewPagerAdapter) =
            CheckFragment().apply {
                this.pagerAdapter = adapter
            }
    }

}