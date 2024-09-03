package dialog

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.hometab.R
import com.example.hometab.databinding.ActivityDatedialogBinding
import com.example.hometab.databinding.ActivityHomefragment3MypageRvBinding
import com.example.hometab.databinding.ActivityInformation1Binding
import com.example.hometab.itshome
import viewmodel.home2ViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import java.util.Calendar
import java.util.Date


class datedialog:  AppCompatActivity() {

    private lateinit var binding: ActivityDatedialogBinding
    var startdate=""
    var enddate=""
    val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    lateinit var real_startdate:String
    lateinit var real_enddate:String

    val viewModel: home2ViewModel by lazy {
        ViewModelProvider(this).get(home2ViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=  ActivityDatedialogBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)


            binding.startdatebtn.setOnClickListener{
                val cal = Calendar.getInstance()    //캘린더뷰 만들기
                val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    binding.startdatebtn.text = "${year}년 ${month+1}월 ${dayOfMonth}일"
                    startdate = "${year}-${month+1}-${dayOfMonth}"
                    real_startdate = startdate.format(pattern)
                    viewModel.givestartvalue(real_startdate)


                }
                DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
            }

            binding.enddatebtn.setOnClickListener{
                val cal = Calendar.getInstance()    //캘린더뷰 만들기
                val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    binding.enddatebtn.text = "${year}년 ${month+1}월 ${dayOfMonth}일"
                    enddate = "${year}-${month+1}-${dayOfMonth}"
                    real_enddate =  enddate.format(pattern)
                    viewModel.giveendvalue(real_enddate)
                }
                DatePickerDialog(this , dateSetListener, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
            }

            binding.dateokbtn.setOnClickListener {
                val intent = Intent()
                intent.putExtra("sample","1");
                setResult(RESULT_OK,intent)
                finish()
            }
//        val currentTime = LocalDateTime.now()
//        val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
//        val write_date: String = currentTime.format(pattern)
    }







}







