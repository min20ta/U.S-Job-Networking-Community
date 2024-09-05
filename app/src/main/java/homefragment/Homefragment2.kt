package homefragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hometab.*
import com.example.hometab.databinding.FragmentHomefragment2Binding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dataclass.rvhome2
import dataclass.writecontent
import dialog.datedialog
import information.information3
import viewmodel.home2ViewModel
import writeactivity.write1


class homefragment2 : Fragment() {

    private lateinit var binding: FragmentHomefragment2Binding

    val viewModel: home2ViewModel by lazy {
        ViewModelProvider(this).get(home2ViewModel::class.java)
    }

//    var rv_Adapter:rvAdapter?=null
    //프래그먼트 변수화


    val Adapter: rvAdapter = rvAdapter()

    lateinit var activityResultLauncher1: ActivityResultLauncher<Intent>
    lateinit var activityResultLauncher2: ActivityResultLauncher<Intent>
    var category1 :String =""
    var category2:String =""
    var category3:String =""
    var content:String = ""
    var writedate:String =""

    var id=App.preferences.getString("id","")



    //refresh 함수적고, write액티비티나,등등에서함수사용
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        binding = FragmentHomefragment2Binding.inflate(inflater, container, false)

        binding.RecyclerView.adapter = Adapter
        binding.RecyclerView.layoutManager = LinearLayoutManager(this.context)


        binding.helpicon.setOnClickListener {
            val subintent = Intent(activity as itshome, information3::class.java)
            startActivity(subintent)
        }

        activityResultLauncher1 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result->

            if (result.resultCode == AppCompatActivity.RESULT_OK) {

                Log.d("filter-kmj", "001")
                viewModel.setFilter()


            }}



        binding.filter.setOnClickListener {
            activityResultLauncher1.launch(Intent(activity as itshome,datedialog::class.java))
//            val intent = Intent(activity as itshome,datedialog::class.java)
//            startActivity(intent)
        }

        viewModel.filteredlist.observe(viewLifecycleOwner, Observer {
            Adapter.submitList(it)
        })




        activityResultLauncher2 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result->

            if (result.resultCode == AppCompatActivity.RESULT_OK) {

                Log.d("resultfab","000")

                category1=result.data?.getStringExtra("clickcategory1").toString()
                category2=result.data?.getStringExtra("clickcategory2").toString()
                category3=result.data?.getStringExtra("clickcategory3").toString()
                content=result.data?.getStringExtra("clickcontent").toString()
                writedate=result.data?.getStringExtra("clickwritedate").toString()

                Log.d("onviewcreatedcontent_kmj","$content")


                 viewModel.addlist(rvhome2(id, category1,category2,category3,"현직자",content,writedate))
//                viewModel.getItem(rvhome2(id, category1,category2,category3,"현직자",content,writedate))
                 viewModel.PostWrtie(writecontent(id, category1,category2,category3,content))
//                Adapter.addData(rvhome2(id, category1,category2,category3,"현직자",content,writedate))

                Log.d("onviewcreated_kmj","${viewModel.copylist}")






            }
        }
        binding.fabBtn.setOnClickListener {
            activityResultLauncher2.launch(Intent(activity as itshome,write1::class.java))

        }

        viewModel.boardlist.observe(viewLifecycleOwner, Observer {

            Adapter.submitList(it)

            Log.d("createview_kmj","$it")



            Adapter.setItemClickListener(object : rvAdapter.OnItemClickListener {
                val intent = Intent(activity as itshome, Homefragment2_detail::class.java)
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


//        binding.RecyclerView.setHasFixedSize(true)

        return binding.root

    }



    override fun onResume() {
        super.onResume()
//        binding.RecyclerView.adapter = Adapter
//        binding.RecyclerView.layoutManager = LinearLayoutManager(this.context)

        viewModel.filteredlist.observe(viewLifecycleOwner, Observer {
            Adapter.submitList(it)
       
        })

