package loginactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.hometab.App
import com.example.hometab.R
import com.example.hometab.databinding.ActivityAccount25Binding
import com.example.hometab.databinding.ActivityAccount2Binding
import dataclass.email

import kotlinx.android.synthetic.main.activity_account2.*


class account2 : AppCompatActivity() {

    private lateinit var binding: ActivityAccount2Binding  //xml을 바인딩
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAccount2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        binding.emailButton.setOnClickListener {

            App.preferences.setString("jobstatus", "현직자")

            val intent = Intent(this, account2_5::class.java)
            startActivity(intent)


        }

        binding.cardimageButton.setOnClickListener {

            App.preferences.setString("jobstatus", "취준생")

            var id = intent.getStringExtra("id")

            val intent = Intent(this, account2_6::class.java)
            intent.putExtra("id", id)
            startActivity(intent)


        }


    }

}