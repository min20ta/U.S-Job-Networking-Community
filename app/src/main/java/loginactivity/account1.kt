package loginactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hometab.R
import com.example.hometab.databinding.ActivityAccount1Binding
import com.example.hometab.databinding.ActivityWrite1Binding
import com.example.hometab.itshome
import com.example.hometab.rvAdapter
import dataclass.person
import dataclass.rvhome2
import information.information1
import kotlinx.android.synthetic.main.activity_account1.*
import viewmodel.home2ViewModel


class account1 : AppCompatActivity() {

    private lateinit var binding: ActivityAccount1Binding  //xml을 바인딩

    val viewModel: home2ViewModel by lazy {
        ViewModelProvider(this).get(home2ViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=  ActivityAccount1Binding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)





        binding.workbtn.setOnClickListener{ //현직자
            val intent = Intent(this, account2::class.java)

            var name = binding.name.text.toString()
            var id = binding.id.text.toString()
            var password = binding.password.text.toString()
            var phonenumber = binding.phonenumber.text.toString()

            viewModel.PostRegister(person(name,id,password,phonenumber))
            intent.putExtra("id",id)
            startActivity(intent)

        }
        binding.noworkbtn.setOnClickListener{  //취준생
            val intent22 = Intent(this, information1::class.java)

            var name = binding.name.text.toString()
            var id = binding.id.text.toString()
            var password = binding.password.text.toString()
            var phonenumber = binding.phonenumber.text.toString()

            viewModel.PostRegister(person(name,id,password,phonenumber))

            startActivity(intent22)

        }
    }

}