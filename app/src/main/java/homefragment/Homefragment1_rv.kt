package homefragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hometab.R
import com.example.hometab.databinding.ActivityHomefragment1RvBinding
import com.example.hometab.databinding.ActivityHomefragment2detailBinding
import com.example.hometab.itshome
import com.example.hometab.rvAdapter
import dataclass.rvhome2
import viewmodel.home2ViewModel


//카테고리누르면 리사이클러뷰 액티비티 보여주는 화면
class Homefragment1_rv : AppCompatActivity() {
    private lateinit var binding: ActivityHomefragment1RvBinding  //xml을 바인딩

    val viewModel: home2ViewModel by lazy {
        ViewModelProvider(this).get(home2ViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=  ActivityHomefragment1RvBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)





        //서버로부터 전체데이터부르기
        val category = intent.getStringExtra("category")
        val categorynum = intent.getStringExtra("categorynum")


        //최신글의 디테일페이지 재활용
        val intent = Intent(this, Homefragment2_detail::class.java)

        viewModel.getCategoryPost("$category", "$categorynum")

        viewModel.categoryclicked.observe(this, Observer {
            val rv_Adapter = rvAdapter()
            binding.RecyclerView2.adapter = rv_Adapter
            binding.RecyclerView2.layoutManager = LinearLayoutManager(this)
            rv_Adapter.submitList(it)


            rv_Adapter.setItemClickListener(object : rvAdapter.OnItemClickListener {
                override fun onClick(view: View, position: Int) {
                    Log.d("click", "${it.get(position).id}")
                    intent.putExtra("id", it.get(position).id)
                    intent.putExtra("category1", it.get(position).category1)
                    intent.putExtra("category2", it.get(position).category2)
                    intent.putExtra("category3", it.get(position).category3)
                    intent.putExtra("jobstatus", it.get(position).jobstatus)
                    intent.putExtra("content", it.get(position).content)
                    intent.putExtra("write_date", it.get(position).write_date)
                    startActivity(intent)


                }
            })
        })

















}
}