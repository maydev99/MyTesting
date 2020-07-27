package com.bombadu.mytesting.util

import androidx.recyclerview.widget.DiffUtil
import com.bombadu.mytesting.database.MyData

class MyDiffCallback : DiffUtil.Callback() {

    lateinit var oldData: List<MyData>
    lateinit var newData: List<MyData>

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition].id == newData[newItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldData.size
    }

    override fun getNewListSize(): Int {
        return newData.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition] == (newData[newItemPosition])
    }
}