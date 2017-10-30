package com.example.nickolas.kpiweeks.utils

import android.support.v7.util.DiffUtil
import com.example.nickolas.kpiweeks.model.enteties.Item


class SimpleDiffCallback internal constructor(private val oldList: List<Item>, private val newList: List<Item>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].equals(newList[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areItemsTheSame(oldItemPosition, newItemPosition)
    }
}