package com.example.hometab

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hometab.databinding.ActivityItshomeBinding
import com.google.android.material.tabs.TabLayoutMediator
import homefragment.searchviewfragment
import kotlinx.android.synthetic.main.activity_itshome.*
import viewmodel.home2ViewModel


class itshome : AppCompatActivity() {

    private lateinit var binding:ActivityItshomeBinding //전역으로 바인딩 객체 선언


    val viewModel: home2ViewModel by lazy {

        ViewModelProvider(this).get(home2ViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItshomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val adapter = vpadapter(this)
        binding.viewpager.adapter = adapter

        val tabTitles = listOf("Category", "최신글", "my","search")

        TabLayoutMediator(binding.tablayout, binding.viewpager) {tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        (binding.tablayout.getChildAt(0) as ViewGroup).getChildAt(3).visibility = View.GONE





        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    if (newText.isNotEmpty()) {
                        binding.tablayout.visibility = View.GONE
                        searchviewfragment().searchtext(newText)
                        binding.tablayout.getTabAt(3)
                        binding.viewpager.currentItem = 3


                    } else {

                        binding.tablayout.visibility = View.VISIBLE
                        binding.tablayout.getTabAt(0)
                        binding.viewpager.currentItem = 0

                    }
                }

                return false
            }

        })

}
