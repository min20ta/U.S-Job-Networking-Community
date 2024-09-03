package loginactivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hometab.databinding.ActivityAccount25Binding
import dataclass.email
import dataclass.person
import kotlinx.android.synthetic.main.activity_account1.*
import viewmodel.home2ViewModel



class account2_5: AppCompatActivity() {

    private lateinit var binding: ActivityAccount25Binding  //xml을 바인딩

    val viewModel: home2ViewModel by lazy {
        ViewModelProvider(this).get(home2ViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAccount25Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        binding.emailbtn.setOnClickListener{ //현직자
            val intent = Intent(this, account3::class.java)

            var myemail = binding.youremail.text.toString()
            viewModel.PostEmail(email(myemail))

            viewModel.emailnumber.observe(this, Observer {
                intent.putExtra("myemailnumber", it.toString())
                  startActivity(intent)
            })



        }

    }}