package com.example.hometab

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import homefragment.homefragment
import homefragment.homefragment2
import homefragment.homefragment3

internal class vpadapter(context: Context,fm:FragmentManager, var totalTabs:Int) :FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
            return when(position){
                0->{
                    homefragment()
                }
                1 ->{
                    homefragment2()
                }
                2->{
                    homefragment3()
                }
                else->getItem(position)
            }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}