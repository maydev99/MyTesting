package com.bombadu.mytesting.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bombadu.mytesting.R
import com.bombadu.mytesting.database.MyData

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyHolder>(){

    private var datas : List<MyData> = ArrayList()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return MyHolder(itemView)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val currentData = datas[position]
        holder.textViewDate.text = currentData.date
        holder.textViewTime.text = currentData.time
        holder.textViewNumber.text = currentData.number.toString()

    }

    fun getDataAt(position: Int) : MyData? {
        return datas[position]
    }

    fun setDatas(datas: List<MyData>) {
        this.datas = datas
        notifyDataSetChanged()
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewDate: TextView = itemView.findViewById(R.id.dateTextView)
        var textViewTime: TextView = itemView.findViewById(R.id.timeTextView)
        var textViewNumber: TextView = itemView.findViewById(R.id.numberTextView)
    }

}