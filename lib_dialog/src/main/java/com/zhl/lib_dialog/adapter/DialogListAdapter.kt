package com.zhl.lib_dialog.adapter

import android.content.Context
import android.database.DataSetObserver
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import com.zhl.lib_dialog.R

/**
 *    author : zhuhl
 *    date   : 2021/6/25
 *    desc   :
 *    refer  :
 */
class DialogListAdapter<T>(private val context: Context, private val list: MutableList<T>) :
    android.widget.ListAdapter {

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
        return true
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View? = null
        var textView: TextView? = null
        if (convertView == null) {
            view = View.inflate(context, R.layout.dialog_common_list_item, null)
        } else {
            view = convertView
        }
        textView = view?.findViewById<TextView>(R.id.tvDialogContent)
        textView?.text = list[position].toString()

        return view ?: throw IllegalStateException("getView cannot null")
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