package loginactivity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.hometab.App
import com.example.hometab.R
import com.example.hometab.RetrofitInstance.getInstance
import com.example.hometab.databinding.ActivityAccount1Binding
import com.example.hometab.databinding.Login1Binding
import com.example.hometab.itshome
import dataclass.login
import dataclass.person
import kotlinx.android.synthetic.main.login1.*
import viewmodel.home2ViewModel
import java.net.CookieManager

class login1 : AppCompatActivity() {


    private lateinit var binding: Login1Binding  //xml을 바인딩


    val viewModel: home2ViewModel by lazy {
        ViewModelProvider(this).get(home2ViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=  Login1Binding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)



        loginbtn2.setOnClickListener {

            var id = binding.yourid.text.toString()
            var password = binding.yourpassword.text.toString()

            viewModel.PostLogin(login(id,password))

            App.preferences.setString("id",id)



            val intent = Intent(this, itshome::class.java)
            startActivity(intent)


        }

    }
}