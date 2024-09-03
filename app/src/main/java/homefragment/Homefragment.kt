package homefragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hometab.R
import com.example.hometab.databinding.FragmentHomefragment2Binding
import com.example.hometab.databinding.FragmentHomefragmentBinding
import com.example.hometab.itshome
import com.example.hometab.rvAdapter
import dataclass.rvhome2
import information.information1
import org.w3c.dom.Text
import viewmodel.home2ViewModel


class homefragment : Fragment() {
    private lateinit var binding: FragmentHomefragmentBinding

    val viewModel: home2ViewModel by lazy {
        ViewModelProvider(this).get(home2ViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = FragmentHomefragmentBinding.inflate(inflater, container, false)

        val intent = Intent(activity as itshome, Homefragment1_rv::class.java)

        binding.helpicon.setOnClickListener{
            val subintent=Intent(activity as itshome, information1::class.java)
            startActivity(subintent)
        }

        //intent로 카테고리내용만 전달하고 다음페이지에서 리사이클러븈불러야하는듯
        //카테고리1클릭
        binding.category11.setOnClickListener {
            var category = binding.category11.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "1")
            startActivity(intent) }
        binding.category12.setOnClickListener {
            var category = binding.category12.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "1")
            startActivity(intent)
        }
        binding.category13.setOnClickListener {
            var category = binding.category13.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "1")
            startActivity(intent)
        }

        //카테고리2클릭
        binding.category21.setOnClickListener {
            var category = binding.category21.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "2")
            startActivity(intent) }
        binding.category22.setOnClickListener {
            var category = binding.category22.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "2")
            startActivity(intent) }
        binding.category23.setOnClickListener {
            var category = binding.category23.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "2")
            startActivity(intent) }
        binding.category24.setOnClickListener {
            var category = binding.category24.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "2")
            startActivity(intent) }
        binding.category25.setOnClickListener {
            var category = binding.category25.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "2")
            startActivity(intent) }
        binding.category26.setOnClickListener {
            var category = binding.category26.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "2")
            startActivity(intent) }
        binding.category27.setOnClickListener {
            var category = binding.category27.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "2")
            startActivity(intent) }
        binding.category28.setOnClickListener {
            var category = binding.category28.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "2")
            startActivity(intent) }
        binding.category29.setOnClickListener {
            var category = binding.category29.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "2")
            startActivity(intent) }
        binding.category210.setOnClickListener {
            var category = binding.category210.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "2")
            startActivity(intent) }
        binding.category211.setOnClickListener {
            var category = binding.category211.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "2")
            startActivity(intent) }
        binding.category212.setOnClickListener {
            var category = binding.category212.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "2")
            startActivity(intent) }


        //카테고리3클릭
        binding.category31.setOnClickListener {
            var category = binding.category31.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "3")
            startActivity(intent) }
        binding.category32.setOnClickListener {
            var category = binding.category32.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "3")
            startActivity(intent) }
        binding.category33.setOnClickListener {
            var category = binding.category33.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "3")
            startActivity(intent) }
        binding.category34.setOnClickListener {
            var category = binding.category34.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "3")
            startActivity(intent) }
        binding.category35.setOnClickListener {
            var category = binding.category35.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "3")
            startActivity(intent) }
        binding.category36.setOnClickListener {
            var category = binding.category36.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "3")
            startActivity(intent) }
        binding.category37.setOnClickListener {
            var category = binding.category37.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "3")
            startActivity(intent) }
        binding.category38.setOnClickListener {
            var category = binding.category38.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "3")
            startActivity(intent) }
        binding.category39.setOnClickListener {
            var category = binding.category39.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "3")
            startActivity(intent) }
        binding.category310.setOnClickListener {
            var category = binding.category310.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "3")
            startActivity(intent) }
        binding.category311.setOnClickListener {
            var category = binding.category311.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "3")
            startActivity(intent) }
        binding.category312.setOnClickListener {
            var category = binding.category312.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "3")
            startActivity(intent) }
        binding.category313.setOnClickListener {
            var category = binding.category313.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "3")
            startActivity(intent) }
        binding.category314.setOnClickListener {
            var category = binding.category314.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "3")
            startActivity(intent) }
        binding.category315.setOnClickListener {
            var category = binding.category315.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "3")
            startActivity(intent) }
        binding.category316.setOnClickListener {
            var category = binding.category316.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "3")
            startActivity(intent) }
        binding.category317.setOnClickListener {
            var category = binding.category317.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "3")
            startActivity(intent) }
        binding.category318.setOnClickListener {
            var category = binding.category318.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "3")
            startActivity(intent) }
        binding.category319.setOnClickListener {
            var category = binding.category319.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "3")
            startActivity(intent) }
        binding.category320.setOnClickListener {
            var category = binding.category320.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "3")
            startActivity(intent) }
        binding.category321.setOnClickListener {
            var category = binding.category321.text.toString().substring(2)
            intent.putExtra("category", category)
            intent.putExtra("categorynum", "3")
            startActivity(intent) }










        return binding.root
    }




}