package homefragment

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hometab.App
import com.example.hometab.R
import com.example.hometab.databinding.ActivityHomefragment3HiddenpageRvBinding
import com.example.hometab.rvAdapter
import com.example.hometab.rvAdapter_hiddenpagebuybtn
import dataclass.hiddendata
import dataclass.writecontent
import viewmodel.home2ViewModel

class Homefragment3_hiddenpage_rv : AppCompatActivity() {


    private lateinit var binding: ActivityHomefragment3HiddenpageRvBinding //xml을 바인딩

    val viewModel: home2ViewModel by lazy {

        ViewModelProvider(this).get(home2ViewModel::class.java)
}

    var id=App.preferences.getString("id","")


override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding=  ActivityHomefragment3HiddenpageRvBinding.inflate(layoutInflater)
    val view=binding.root
    setContentView(view)




    //서버로부터 내가쓴글전체데이터부르기
    viewModel.gethiddenpage()


    //내포인트로 구매능력확인
    val id=App.preferences.getString("id","")
    lateinit var canIbuy:String

//    viewModel.GetMypageCoin(id)
    viewModel.coin.observe(this, Observer {

        if(it.toInt() >= 500){

           canIbuy="yes"

        }

        else{

            canIbuy="no"
        }
    })


    //최신글의 디테일페이지 재활용
    val intent = Intent(this, Homefragment2_detail::class.java)


    //리사이클러뷰
    viewModel.hiddenpage.observe(this, Observer {

        val rvAdapter = rvAdapter_hiddenpagebuybtn(it as ArrayList<hiddendata>)
        binding.RecyclerView3Hidden.adapter = rvAdapter
        binding.RecyclerView3Hidden.layoutManager = LinearLayoutManager(this)


        rvAdapter.setItemClickListener(object : rvAdapter_hiddenpagebuybtn.OnItemClickListener {

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


            override fun onBuybtnClick(view: View, position: Int) {

              AlertDialog.Builder(view.context).apply {
                    setTitle("숨김글 보기")
                    setMessage("500포인트를 차감하시겠습니까?")

                    setPositiveButton("확인", DialogInterface.OnClickListener{ dialog, which->

                        if (canIbuy=="no"){

                            Toast.makeText(view.context, "포인트가 부족합니다", Toast.LENGTH_SHORT).show()
                        }

                        else{

                        Toast.makeText(view.context, "500포인트가 차감되었습니다", Toast.LENGTH_SHORT).show()

                        var writerid=it.get(position).id
                        var category1=it.get(position).category1
                        var category2=it.get(position).category2
                        var category3=it.get(position).category3
                        var content=it.get(position).content


                        viewModel.PostBoughtHiddenpage("$id",
                            writecontent(writerid,category1,category2,category3,content)
                        )


                        val hiddenpageBuybtn: Button =view.findViewById(R.id.hiddenpageBuybtn)
                        hiddenpageBuybtn.visibility=View.INVISIBLE

                        }

                    })
                    setNegativeButton("취소", DialogInterface.OnClickListener { dialog, which ->

                    })
                    show()
                }


            }
        })

    })





}
}