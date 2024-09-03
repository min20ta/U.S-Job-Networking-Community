package information

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hometab.R
import com.example.hometab.databinding.ActivityInformation1Binding
import com.example.hometab.databinding.ActivityInformation2Binding
import com.example.hometab.databinding.ActivityItshomeBinding

class information2: AppCompatActivity() {

    private lateinit var binding: ActivityInformation2Binding //전역으로 바인딩 객체 선언

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= ActivityInformation2Binding.inflate(layoutInflater)
        setContentView(binding.root)










    }}
