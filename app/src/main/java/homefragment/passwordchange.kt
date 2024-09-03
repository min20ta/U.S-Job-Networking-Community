package homefragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.hometab.App
import com.example.hometab.R
import com.example.hometab.databinding.ActivityAccount1Binding.inflate
import com.example.hometab.databinding.ActivityHomefragment2detailBinding
import com.example.hometab.databinding.ActivityPasswordchangeBinding
import com.example.hometab.databinding.FragmentHomefragment3Binding
import com.example.hometab.itshome
import dataclass.login
import kotlinx.android.synthetic.main.activity_passwordchange.*
import viewmodel.home2ViewModel


class passwordchange : AppCompatActivity() {

    private lateinit var binding: ActivityPasswordchangeBinding  //xml을 바인딩


    val viewModel: home2ViewModel by lazy {
        ViewModelProvider(this).get(home2ViewModel::class.java)
    }

    val manager=supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityPasswordchangeBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)




        binding.newpasswordAgain.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                // text가 변경된 후 호출
                // s에는 변경 후의 문자열이 담겨 있다.
                if(binding.newpasswordAgain.text.toString()==binding.newpassword.text.toString()){
                    binding.passwordchangeWarning.setText("비밀번호가 일치합니다")

                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // text가 변경되기 전 호출
                // s에는 변경 전 문자열이 담겨 있다.

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // text가 바뀔 때마다 호출된다.
               binding.passwordchangeWarning.setText("비밀번호가 일치하지 않습니다")
            }
        })






       binding.passwordchangebtn.setOnClickListener{

           val password=binding.newpasswordAgain.text.toString()
           var id= App.preferences.getString("id","")

           viewModel.PostChangePassword(login(id,password))


           val intent = Intent(this, itshome::class.java)
           startActivity(intent)


           Toast.makeText(view.context, "비밀번호가 변경되었습니다", Toast.LENGTH_SHORT).show()
        }

}}

