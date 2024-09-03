package homefragment

import android.app.PendingIntent.getActivity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hometab.App
import com.example.hometab.databinding.ActivityHomefragment3MypageRvBinding
import com.example.hometab.itshome
import com.example.hometab.rvAdapter_mypagehiddenbtn
import dataclass.rvhome2
import dataclass.writecontent
import viewmodel.home2ViewModel


//카테고리누르면 리사이클러뷰 액티비티 보여주는 화면
class Homefragment3_mypage_rv : AppCompatActivity() {
    private lateinit var binding: ActivityHomefragment3MypageRvBinding  //xml을 바인딩

    val viewModel: home2ViewModel by lazy {
        ViewModelProvider(this).get(home2ViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=  ActivityHomefragment3MypageRvBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)




        //서버로부터 내가쓴글전체데이터부르기
        var id= App.preferences.getString("id","")
        viewModel.getMypageMywritesPost("$id")


        //최신글의 디테일페이지 재활용
        val intent = Intent(this, Homefragment2_detail::class.java)


        //리사이클러뷰
        viewModel.mypage_mywrites.observe(this, Observer {

            val rvAdapter = rvAdapter_mypagehiddenbtn(it as ArrayList<rvhome2>)




            rvAdapter.setItemClickListener(object : rvAdapter_mypagehiddenbtn.OnItemClickListener {

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

                override fun onHiddenbtnClick(view: View,position:Int) {
                    Log.d("hidden","clicked")

                   AlertDialog.Builder(view.context).apply {
                        setTitle("작성한 글 숨기기")
                        setMessage("숨기기처리를 하시겠습니까?")
                        setPositiveButton("확인",DialogInterface.OnClickListener{dialog,which->

                            var id=App.preferences.getString("id","")
                            var category1=it.get(position).category1
                            var category2=it.get(position).category2
                            var category3=it.get(position).category3
                            var content=it.get(position).content

                            Toast.makeText(view.context, "숨김처리 되었습니다", Toast.LENGTH_SHORT).show()

                            viewModel.PostMypageMyWritesHidden(id,writecontent(id,category1,category2,category3,content))

                        })
                       setNegativeButton("취소", DialogInterface.OnClickListener { dialog, which ->

                       })
                       show()
                }
                }


                override fun onDeletebtnCLick(view: View,position:Int) {
                    Log.d("hidden","clicked")

                    AlertDialog.Builder(view.context).apply {
                        setTitle("관리자에게 글 숨기기요청")
                        setMessage("개인정보나 신상을 특정할 수 있는 민감정보가 포함되어있을시 숨김게시판에도 글이 보이지않도록 관리자에게 숨기기요청을 할 수 있습니다")
                        setPositiveButton("숨기기요청",DialogInterface.OnClickListener{dialog,which->

//                            var id=App.preferences.getString("id","")
//                            var category1=it.get(position).category1
//                            var category2=it.get(position).category2
//                            var category3=it.get(position).category3
//                            var content=it.get(position).content

                            Toast.makeText(view.context, "담당자확인까지 48시간이 소요됩니다", Toast.LENGTH_LONG).show()

//                            viewModel.PostMypageMyWritesHidden(id,writecontent(id,category1,category2,category3,content))

                        })
                        setNegativeButton("취소", DialogInterface.OnClickListener { dialog, which ->

                        })
                        show()
                    }
                }







            })
            binding.RecyclerView3.adapter = rvAdapter
            binding.RecyclerView3.layoutManager = LinearLayoutManager(this)


        })
//        binding.RecyclerView3.setHasFixedSize(true)




    }
}