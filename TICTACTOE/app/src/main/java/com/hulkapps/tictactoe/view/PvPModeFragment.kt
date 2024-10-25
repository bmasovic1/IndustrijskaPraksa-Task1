package com.hulkapps.tictactoe.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import com.hulkapps.tictactoe.R
import java.lang.Exception


class PvPModeFragment : Fragment() {


    private lateinit var pl: ImageButton;
    private lateinit var ai: ImageButton;
    private lateinit var pagerAdapter: ViewPagerAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_pvp_mode, container, false)

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                try{
                    pagerAdapter.change(0, FirstFragment.newInstance(pagerAdapter))
                }
                catch (e: java.lang.Exception){}

            }
        })

        pl = view.findViewById(R.id.online)
        ai = view.findViewById(R.id.offline)


        try{
            pagerAdapter.remove(1)
        }
        catch (e:Exception){}

        pl.setOnClickListener{

            pagerAdapter.change(0, CreateJoinFragment.newInstance(pagerAdapter))

        }

        ai.setOnClickListener{

            pagerAdapter.change(0, OfflineGameFragment.newInstance(pagerAdapter))
            pagerAdapter.add(1, SettingsFragment.newInstance(pagerAdapter))

        }

        return view

    }

    companion object {
        fun newInstance(adapter: ViewPagerAdapter) =
            PvPModeFragment().apply {
                this.pagerAdapter = adapter
            }
    }

}


