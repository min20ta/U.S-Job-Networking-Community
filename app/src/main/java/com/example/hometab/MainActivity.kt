package com.example.hometab

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_homefragment3.*
import loginactivity.account1
import loginactivity.login1


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//mainpage에서 이동 (activity->activity)
        loginbtn.setOnClickListener(){
            val intent= Intent(this, login1::class.java)
            startActivity(intent)

        }
        accountbtn.setOnClickListener(){
            val intent2= Intent(this, account1::class.java)
            startActivity(intent2)
        }







        }}





