package com.example.hometab

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dataclass.rvhome2


class rvAdapter_search : ListAdapter<rvhome2, rvAdapter_search.ViewHolder>(diffUtil), Filterable {

    var list = mutableListOf<rvhome2>()


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val idtextView: TextView = view.findViewById(R.id.recycleid)
        val category1textView: TextView = view.findViewById(R.id.recyclecategory1)
        val category2textView: TextView = view.findViewById(R.id.recyclecategory2)
        val category3textView: TextView = view.findViewById(R.id.recyclecategory3)
        val jobstatustextView: TextView = view.findViewById(R.id.recyclejobstatus)
        val contenttextView: TextView = view.findViewById(R.id.recyclecontent)
        val write_datetextView: TextView = view.findViewById(R.id.write_date)


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_write, parent, false)
        return ViewHolder(view)
    }    //여기는 아이템담을 그릇? 공간정의하고

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.idtextView.text = list[position].id
        holder.category1textView.text = list[position].category1
        holder.category2textView.text = list[position].category2
        holder.category3textView.text = list[position].category3
        holder.jobstatustextView.text = list[position].jobstatus
        holder.contenttextView.text =list[position].content
        holder.write_datetextView.text = list[position].write_date

        holder.itemView.setOnClickListener {  //호출
            itemClickListener.onClick(it, position)


        }
    }


    override fun getFilter(): Filter {
        return searchFilter
    }

    private val searchFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList = ArrayList<rvhome2>()

            if (constraint.toString().isEmpty()) {
                filteredList.addAll(list)
            } else {
                val filterPattern = constraint.toString()
                for (item in list) {
                    //filter 대상 setting
                    Log.d("yeah", item.toString())
                    if (item.content.contains(filterPattern)) {
                        filteredList.add(item)
//                        { it <= ' ' }
                    }
                }
            }
            Log.d("filter", filteredList.toString())
            val results = FilterResults()
            results.values = filteredList
            return results
        }


        override fun publishResults(constraint: CharSequence, results: FilterResults) {
//            list.clear()
//            list.addAll(results.values as  ArrayList<rvhome2>)
            submitList(results?.values as  ArrayList<rvhome2>)
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
                oldItem == newItem

            override fun areItemsTheSame(oldItem: rvhome2, newItem: rvhome2) =
                oldItem.content == newItem.content
        }


    }

    fun setter(list: ArrayList<rvhome2>) {
     this.list = list!!
     submitList(list)
    }
}