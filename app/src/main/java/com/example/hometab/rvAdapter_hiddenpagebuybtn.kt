package com.example.hometab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dataclass.hiddendata



class rvAdapter_hiddenpagebuybtn(private val dataSet:ArrayList<hiddendata>): RecyclerView.Adapter<rvAdapter_hiddenpagebuybtn.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idtextView: TextView = view.findViewById(R.id.recycleid)
        val category1textView: TextView = view.findViewById(R.id.recyclecategory1)
        val category2textView: TextView = view.findViewById(R.id.recyclecategory2)
        val category3textView: TextView = view.findViewById(R.id.recyclecategory3)
        val jobstatustextView: TextView = view.findViewById(R.id.recyclejobstatus)
        val contenttextView: TextView = view.findViewById(R.id.recyclecontent)
        val hiddenpageBuybtn: Button =view.findViewById(R.id.hiddenpageBuybtn)
        val write_datetextView:TextView=view.findViewById(R.id.write_date)





    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_write_hidden, parent, false)
        return ViewHolder(view)
    }    //여기는 아이템담을 그릇? 공간정의하고

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var id=App.preferences.getString("id","")

        holder.idtextView.text = dataSet[position].id
        holder.category1textView.text = dataSet[position].category1
        holder.category2textView.text = dataSet[position].category2
        holder.category3textView.text = dataSet[position].category3
        holder.jobstatustextView.text = dataSet[position].jobstatus
        holder.contenttextView.text = dataSet[position].content
        holder.write_datetextView.text = dataSet[position].write_date


        if (dataSet[position].buyer==id){
            holder.hiddenpageBuybtn.visibility=View.INVISIBLE
        }



        holder.contenttextView.setOnClickListener {  //호출
            itemClickListener.onClick(it, position)

        }
        holder.hiddenpageBuybtn.setOnClickListener{
            itemClickListener.onBuybtnClick(it,position)
        }
    }


    override fun getItemCount(): Int {
        return dataSet.size
    }


    interface OnItemClickListener {
        fun onClick(view: View, position: Int)
        fun onBuybtnClick(view: View, position: Int)
    }

    private lateinit var itemClickListener: OnItemClickListener   //전달될 객체저장
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {  //객체전달
        this.itemClickListener =onItemClickListener
    }



}