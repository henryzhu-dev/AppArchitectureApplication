package com.zhl.baselibrary.adapter

import android.content.Context
import android.database.DataSetObserver
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView

/**
 *    author : zhuhl
 *    date   : 2021/6/25
 *    desc   :
 *    refer  :
 */
class DialogListAdapter<T>(private val context: Context, private val list: MutableList<T>) : android.widget.ListAdapter {

    override fun registerDataSetObserver(observer: DataSetObserver?) {

    }

    override fun unregisterDataSetObserver(observer: DataSetObserver?) {
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): T {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val textView = TextView(context)
//        textView.text = list.get(position).toString()
        return textView
    }

    override fun getItemViewType(position: Int): Int {
        return Adapter.IGNORE_ITEM_VIEW_TYPE
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun isEmpty(): Boolean {
        return false
    }

    override fun areAllItemsEnabled(): Boolean {
        return true
    }

    override fun isEnabled(position: Int): Boolean {
        return false
    }

}