package com.bombadu.mytesting.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.bombadu.mytesting.R
import com.bombadu.mytesting.database.MyData


class MyAdapter : ListAdapter<MyData, MyAdapter.MyHolder>(DataDiffCallback()) {


    class DataDiffCallback : DiffUtil.ItemCallback<MyData>() {
        override fun areItemsTheSame(oldItem: MyData, newItem: MyData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MyData, newItem: MyData): Boolean {
            return oldItem.number == newItem.number && oldItem.date == newItem.date
                && oldItem.time == newItem.time
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return MyHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val currentData = getItem(position)
        holder.textViewDate.text = currentData.date
        holder.textViewTime.text = currentData.time
        holder.textViewNumber.text = currentData.number.toString()

    }

    fun getDataAt(position: Int): MyData? {
        return getItem(position)
    }


    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewDate: TextView = itemView.findViewById(R.id.dateTextView)
        var textViewTime: TextView = itemView.findViewById(R.id.timeTextView)
        var textViewNumber: TextView = itemView.findViewById(R.id.numberTextView)
    }


}