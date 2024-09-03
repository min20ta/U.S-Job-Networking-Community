package com.example.hometab

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dataclass.rvhome2
import viewmodel.home2ViewModel
import java.util.Locale


//ListAdapter<rvhome2,rvAdapter.ViewHolder>(diffUtil)
//diff로 바꾸고 프래그먼트에서 뷰모델추가하는ㄱ거지
//블로그의 restart
class rvAdapter: ListAdapter<rvhome2, rvAdapter.ViewHolder>(diffUtil){

//    var List = mutableListOf<rvhome2>()
//
//    var searchList=mutableListOf<rvhome2>()
//    val searchListAll = currentList.toMutableList()




  inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val idtextView: TextView = view.findViewById(R.id.recycleid)
        val category1textView: TextView = view.findViewById(R.id.recyclecategory1)
        val category2textView: TextView = view.findViewById(R.id.recyclecategory2)
        val category3textView: TextView = view.findViewById(R.id.recyclecategory3)
        val jobstatustextView: TextView = view.findViewById(R.id.recyclejobstatus)
        val contenttextView: TextView = view.findViewById(R.id.recyclecontent)
        val write_datetextView:TextView=view.findViewById(R.id.write_date)



    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_write, parent, false)
        return ViewHolder(view)
    }    //여기는 아이템담을 그릇? 공간정의하고

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.idtextView.text = currentList[position].id
        holder.category1textView.text = currentList[position].category1
        holder.category2textView.text = currentList[position].category2
        holder.category3textView.text = currentList[position].category3
        holder.jobstatustextView.text =currentList[position].jobstatus
        holder.contenttextView.text = currentList[position].content
        holder.write_datetextView.text = currentList[position].write_date

        holder.itemView.setOnClickListener {  //호출
            itemClickListener.onClick(it, position)


        }
    }

        interface OnItemClickListener {
        fun onClick(view: View, position: Int)
    }

    private lateinit var itemClickListener: OnItemClickListener   //전달될 객체저장
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {  //객체전달
        this.itemClickListener = onItemClickListener
    }


    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<rvhome2>() {
            override fun areContentsTheSame(oldItem: rvhome2, newItem: rvhome2) =
                oldItem== newItem

            override fun areItemsTheSame(oldItem: rvhome2, newItem: rvhome2) =
                oldItem.content== newItem.content
        }


    }
//
//    fun setter(list: ArrayList<rvhome2>){
//        searchListAll.addAll(list)
//
//    }

//    override fun getItemCount(): Int {
//
//        return List.size
//    }


//    fun addData(list: rvhome2) {
//        List.add(data)
////        notifyDataSetChanged()
//        submitList(List)
//    }

//    fun setData(data: ArrayList<rvhome2>){
////        List.clear()
////        List.addAll(data)
//        currentList.addAll(data)
//        submitList(currentList)
////        notifyDataSetChanged()
////        data.forEach{List.add(it.copy())}
//
////        notifyDataSetChanged()  //이게있어야 화면에 데이터나옴ㅑ
//    }
//    fun appendList(list: ArrayList<rvhome2>) {
//        val currentList = currentList.toMutableList() // get the current adapter list as a mutated list
//        currentList.addAll(list)
//        submitList(currentList)
//    }
////
//    fun setData(data : ArrayList<rvhome2>) {
//
//    }
//    fun clearList() {
//        List.clear()
//
//    }


}




