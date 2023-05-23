package com.example.hometab

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.appcompat.app.ActionBar
import androidx.viewpager.widget.ViewPager
import com.example.hometab.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import android.text.Editable
import android.text.TextWatcher
import android.transition.Slide
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
//activity간 화면전환시 manifest등록필수!!

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager:ViewPager


    override fun onCreate(savedInstanceState: Bundle?) {

        //WriteActivity가 생성될 때 애니메이션 추가
//        with(window){
//            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
//            // set an slide transition
//            enterTransition = Slide().also {
//                it.slideEdge = Gravity.BOTTOM
//            }
//            exitTransition = Slide().also {
//                it.slideEdge = Gravity.TOP
//            }
//        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //fab버튼 클릭시 새 액티비티 생성
        val fabButton=findViewById<FloatingActionButton>(R.id.fab_btn)
        fabButton.setOnClickListener {
            val intent = Intent(this, writing::class.java)
            startActivity(intent)}
         //  애니메이션 옵션 추가
//            val options = ActivityOptions.makeSceneTransitionAnimation(this)
//            startActivity(intent, options.toBundle())}





//        홈화면 탭
        tabLayout = findViewById(R.id.tablayout)
        viewPager = findViewById(R.id.viewpager)


        val adapter = vpadapter(this, supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}





        })




////    메뉴
//        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                return false
//            }
//        })


    }



}