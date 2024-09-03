package writeactivity

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.hometab.App
import com.example.hometab.R
import com.example.hometab.databinding.ActivityHomefragment1RvBinding
import com.example.hometab.databinding.ActivityWrite1Binding
import com.example.hometab.itshome
import dataclass.rvhome2
import dataclass.writecontent
import kotlinx.android.synthetic.main.activity_write1.*
import kotlinx.android.synthetic.main.activity_write2.categorysavebtn
import viewmodel.home2ViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class write1 : AppCompatActivity() {
    private lateinit var binding: ActivityWrite1Binding  //xml을 바인딩

    val viewModel: home2ViewModel by lazy {
        ViewModelProvider(this).get(home2ViewModel::class.java)
    }

    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    lateinit var yourcategory1:String
    lateinit var yourcategory2:String
    lateinit var yourcategory3:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=  ActivityWrite1Binding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)



        //카테고리선택한거 정보들고오기위한 코드
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            if (it.resultCode == RESULT_OK) {
                yourcategory1=it.data?.getStringExtra("clickcategory1").toString()
                yourcategory2=it.data?.getStringExtra("clickcategory2").toString()
                yourcategory3=it.data?.getStringExtra("clickcategory3").toString()

                var yourcategoryfinal=yourcategory1+"/"+yourcategory2+"/"+yourcategory3
                binding.writing1Category.text=yourcategoryfinal
            }
        }
        binding.writing1Category.setOnClickListener {
            val intent = Intent(this, write2::class.java)
            activityResultLauncher.launch(intent)


        }


        //서버로 정보넘겨야함-쿠키어케하는지공부하고다시
        //페이지도 되돌아갸야함-ok

        binding.savebtn.setOnClickListener{


            var id=App.preferences.getString("id","")
            var content=binding.yourcontent.text.toString()

            val currentTime = LocalDateTime.now()
            val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val write_date: String = currentTime.format(pattern)
//            val parse = LocalDate.parse(patterned, pattern)


//            viewModel.getItem(rvhome2(id, yourcategory1,yourcategory2,yourcategory3,"현직자",content,write_date))
//            viewModel.addlist(rvhome2(id, yourcategory1,yourcategory2,yourcategory3,"현직자",content,write_date))

//            viewModel.PostWrtie(
//                writecontent(id, yourcategory1,yourcategory2,yourcategory3,content)
//            )

            Toast.makeText(this, "저장하였습니다\n500포인트가 추가되었습니다", Toast.LENGTH_SHORT).show();

            //다시 이페이지로 돌아옴
            val intent222 = Intent()
            intent222.putExtra("clickcategory1", yourcategory1)
            intent222.putExtra("clickcategory2", yourcategory2)
            intent222.putExtra("clickcategory3", yourcategory3)
            intent222.putExtra("clickcontent", content)
            intent222.putExtra("clickwritedate", write_date)
            setResult(RESULT_OK,intent222)
            finish()



            }



        }


    }
