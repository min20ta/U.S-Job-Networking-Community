package writeactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.hometab.R
import com.example.hometab.databinding.ActivityWrite1Binding
import com.example.hometab.databinding.ActivityWrite2Binding
import kotlinx.android.synthetic.main.activity_write2.*
import viewmodel.home2ViewModel


class write2 : AppCompatActivity() {

    private lateinit var binding: ActivityWrite2Binding  //xml을 바인딩

    val viewModel: home2ViewModel by lazy {
        ViewModelProvider(this).get(home2ViewModel::class.java)
    }

    lateinit var clickcategory1 : String
    lateinit var clickcategory2 : String
    lateinit var clickcategory3 : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=  ActivityWrite2Binding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)



        binding.rg1.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                    R.id.category1_1 -> clickcategory1=binding.category11.text.toString()
                    R.id.category1_2 -> clickcategory1=binding.category12.text.toString()
                    R.id.category1_3 -> clickcategory1=binding.category13.text.toString()

            }
        }


        binding.rg2.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.category2_1 -> clickcategory2=binding.category21.text.toString()
                R.id.category2_2 -> clickcategory2=binding.category22.text.toString()
                R.id.category2_3 -> clickcategory2=binding.category23.text.toString()
                R.id.category2_4 -> clickcategory2=binding.category24.text.toString()
                R.id.category2_5 -> clickcategory2=binding.category25.text.toString()
                R.id.category2_6 -> clickcategory2=binding.category26.text.toString()
                R.id.category2_7 -> clickcategory2=binding.category27.text.toString()
                R.id.category2_8 -> clickcategory2=binding.category28.text.toString()
                R.id.category2_9 -> clickcategory2=binding.category29.text.toString()
                R.id.category2_10 -> clickcategory2=binding.category210.text.toString()
                R.id.category2_11 -> clickcategory2=binding.category211.text.toString()
                R.id.category2_12 -> clickcategory2=binding.category212.text.toString()

            }
        }


        binding.rg3.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.category3_1 -> clickcategory3=binding.category31.text.toString()
                R.id.category3_2 -> clickcategory3=binding.category32.text.toString()
                R.id.category3_3 -> clickcategory3=binding.category33.text.toString()
                R.id.category3_4 -> clickcategory3=binding.category34.text.toString()
                R.id.category3_5 -> clickcategory3=binding.category35.text.toString()
                R.id.category3_6 -> clickcategory3=binding.category36.text.toString()
                R.id.category3_7 -> clickcategory3=binding.category37.text.toString()
                R.id.category3_8 -> clickcategory3=binding.category38.text.toString()
                R.id.category3_9 -> clickcategory3=binding.category39.text.toString()
                R.id.category3_10 -> clickcategory3=binding.category310.text.toString()
                R.id.category3_11 -> clickcategory3=binding.category311.text.toString()
                R.id.category3_12 -> clickcategory3=binding.category312.text.toString()
                R.id.category3_13 -> clickcategory3=binding.category313.text.toString()
                R.id.category3_14 -> clickcategory3=binding.category314.text.toString()
                R.id.category3_15 -> clickcategory3=binding.category315.text.toString()
                R.id.category3_16 -> clickcategory3=binding.category316.text.toString()
                R.id.category3_17 -> clickcategory3=binding.category317.text.toString()
                R.id.category3_18 -> clickcategory3=binding.category318.text.toString()
                R.id.category3_19 -> clickcategory3=binding.category319.text.toString()
                R.id.category3_20 -> clickcategory3=binding.category320.text.toString()
                R.id.category3_21 -> clickcategory3=binding.category321.text.toString()



            }
        }


        binding.categorysavebtn.setOnClickListener {
            val intent = Intent(this, write1::class.java)
            intent.putExtra("clickcategory1", clickcategory1)
            intent.putExtra("clickcategory2", clickcategory2)
            intent.putExtra("clickcategory3", clickcategory3)
            setResult(RESULT_OK,intent)
            finish()

        }
    }
}