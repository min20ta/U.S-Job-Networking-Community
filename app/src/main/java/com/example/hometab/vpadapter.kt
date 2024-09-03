package com.example.hometab

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import homefragment.homefragment
import homefragment.homefragment2
import homefragment.homefragment3
import homefragment.searchviewfragment

class vpadapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
    val fragmentList = listOf(homefragment(), homefragment2(), homefragment3(),searchviewfragment())
//    private val fragmentIds = fragmentList.map { it.hashCode().toLong() }

    override fun createFragment(position: Int): Fragment {
           return fragmentList[position]
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }
//    override fun getItemId(position: Int): Long {
//        return fragmentList[position].hashCode().toLong()
//    }
//
//    override fun containsItem(itemId: Long): Boolean {
//        return fragmentList.any { it.id == itemId }
//    }
//
//    fun changefragment():  {
//
//    }

}