package homefragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hometab.App
import com.example.hometab.databinding.FragmentSearchviewfragmentBinding
import com.example.hometab.itshome
import com.example.hometab.rvAdapter
import com.example.hometab.rvAdapter_search
import viewmodel.home2ViewModel


class searchviewfragment : Fragment() {

    private lateinit var binding: FragmentSearchviewfragmentBinding

    val viewModel: home2ViewModel by lazy {
        ViewModelProvider(this).get(home2ViewModel::class.java)
    }

    val rvAdaptersearch: rvAdapter_search = rvAdapter_search()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        binding = FragmentSearchviewfragmentBinding.inflate(inflater, container, false)

        binding.RecyclerView.adapter = rvAdaptersearch
        binding.RecyclerView.layoutManager = LinearLayoutManager(this.context)


        viewModel.boardlist.observe(viewLifecycleOwner, Observer {
            rvAdaptersearch.setter(it)

        })


//        viewModel.boardlist.observe(viewLifecycleOwner, Observer {
//
//            Adapter.submitList(it)
//
//            Log.d("createview_kmj", "$it")
//
//
//
//            Adapter.setItemClickListener(object : rvAdapter.OnItemClickListener {
//                val intent = Intent(activity as itshome, Homefragment2_detail::class.java)
//                override fun onClick(view: View, position: Int) {
//                    Log.d("click", "${it.get(position).id}")
//                    intent.putExtra("id", it.get(position).id)
//                    intent.putExtra("category1", it.get(position).category1)
//                    intent.putExtra("category2", it.get(position).category2)
//                    intent.putExtra("category3", it.get(position).category3)
//                    intent.putExtra("jobstatus", it.get(position).jobstatus)
//                    intent.putExtra("content", it.get(position).content)
//                    intent.putExtra("write_date", it.get(position).write_date)
//                    startActivity(intent)
//
//
//                }
//            })
//
//
//        })



        return binding.root
    }

    fun searchtext(text:String){
      rvAdaptersearch.filter.filter(text)

    }
}