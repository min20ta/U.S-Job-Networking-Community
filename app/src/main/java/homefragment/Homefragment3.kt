package homefragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hometab.App
import com.example.hometab.MainActivity

import com.example.hometab.databinding.FragmentHomefragment3Binding
import com.example.hometab.itshome
import information.information2
import information.information3
import loginactivity.account2
import viewmodel.home2ViewModel


class homefragment3 : Fragment() {

    private lateinit var binding: FragmentHomefragment3Binding  //xml을 바인딩


    val viewModel: home2ViewModel by lazy {
        ViewModelProvider(this).get(home2ViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomefragment3Binding.inflate(inflater, container, false)

        binding.helpicon.setOnClickListener {
            val subintent=Intent(activity as itshome, information2::class.java)
            startActivity(subintent)
        }


        //아이디
        val id=App.preferences.getString("id","")
        binding.mypageId.text= id


        //포인트
        viewModel.GetMypageCoin(id)
        viewModel.coin.observe(viewLifecycleOwner, Observer {
            binding.coin.text=it
        })


        //내가쓴글
        binding.homefragment3WhatIwrotebtn.setOnClickListener{

                val intent = Intent(activity as itshome, Homefragment3_mypage_rv::class.java)
                startActivity(intent)
            }


        //로그아웃
        binding.logout.setOnClickListener {

                val intent = Intent(activity as itshome, MainActivity::class.java)
                startActivity(intent)
            }

        //현직자인증
        binding.provebtn.setOnClickListener {

            val intent = Intent(activity as itshome, account2::class.java)
            startActivity(intent)
        }


        //숨김게시판
        binding.homefragment3Hiddenbtn.setOnClickListener{

            val intent = Intent(activity as itshome, Homefragment3_hiddenpage_rv::class.java)
            startActivity(intent)
        }


        //비밀번호변경
        binding.changepassword.setOnClickListener{

            val intent = Intent(activity as itshome, passwordchange::class.java)
            startActivity(intent)
        }
















        return binding.root
    }

}