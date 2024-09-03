package loginactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hometab.R
import com.example.hometab.databinding.ActivityAccount3Binding
import com.example.hometab.rvAdapter
import dataclass.rvhome2
import kotlinx.android.synthetic.main.activity_account3.*
import viewmodel.home2ViewModel


class account3 : AppCompatActivity() {

    private lateinit var binding: ActivityAccount3Binding  //xml을 바인딩

    val viewModel: home2ViewModel by lazy {
        ViewModelProvider(this).get(home2ViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account3)

        binding = ActivityAccount3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        binding.certifibtn.setOnClickListener {


            var emailnumber = binding.emailnumber.text.toString()   //사용자입력
            Log.d("edit","$emailnumber")


            var realemailnumber=intent.getStringExtra("myemailnumber")
            Log.d("view","$realemailnumber")


            if(realemailnumber==(emailnumber))
                {val intent = Intent(this, account4::class.java)   //intent위치중요! 전페이지에서 intent로 값받아오고 나서 다음페이지로 넘어가는 intent정의
                startActivity(intent)}
            else
                 {Toast.makeText(this, "인증번호가 다릅니다", Toast.LENGTH_SHORT).show()}





        }
    }
}