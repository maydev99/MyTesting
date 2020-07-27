package com.bombadu.mytesting.util

import androidx.recyclerview.widget.DiffUtil
import com.bombadu.mytesting.database.MyData

class MyDataDiffCallback(
    private val oldList: List<MyData>,
    private val newList: List<MyData>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return  oldList[oldItemPosition].number == newList[newItemPosition].number
    }


}