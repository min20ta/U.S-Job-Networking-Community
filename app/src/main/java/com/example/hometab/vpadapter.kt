package com.example.hometab

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

internal class vpadapter(var context: Context, fm:FragmentManager, var totalTabs:Int) :FragmentPagerAdapter(fm){
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