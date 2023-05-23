package com.example.hometab

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class writing: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.writing1)

        val txt=findViewById<TextView>(R.id.writing1_category)
        txt.setOnClickListener(){
            val intent=Intent(this, writing2::class.java)
            startActivity(intent)
        }
}}