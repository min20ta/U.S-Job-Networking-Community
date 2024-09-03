package homefragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import androidx.lifecycle.ViewModelProvider
import com.example.hometab.R
import com.example.hometab.databinding.ActivityHomefragment2detailBinding
import com.example.hometab.databinding.FragmentHomefragment2Binding


import viewmodel.home2ViewModel


class Homefragment2_detail  : AppCompatActivity()  {

    private lateinit var binding: ActivityHomefragment2detailBinding  //xml을 바인딩


    val viewModel: home2ViewModel by lazy {
        ViewModelProvider(this).get(home2ViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityHomefragment2detailBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)



        binding.drecycleid.text=intent.getStringExtra("id")
        binding.drecyclecategory1.text = intent.getStringExtra("category1")
        binding.drecyclecategory2.text = intent.getStringExtra("category2")
        binding.drecyclecategory3.text = intent.getStringExtra("category3")
        binding.drecyclejobstatus.text =intent.getStringExtra("jobstatus")
        binding.drecyclecontent.text = intent.getStringExtra("content")
        binding.drecyclewritedate.text=intent.getStringExtra("write_date")









    }}



