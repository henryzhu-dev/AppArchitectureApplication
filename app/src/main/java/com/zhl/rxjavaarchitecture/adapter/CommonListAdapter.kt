package com.zhl.rxjavaarchitecture.adapter

import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhl.rxjavaarchitecture.R
import com.zhl.rxjavaarchitecture.model.CategoriesListItem
import com.zhl.rxjavaarchitecture.model.CategoriesListResponse

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   :
 */
class CommonListAdapter(list: MutableList<CategoriesListItem>) :
    BaseQuickAdapter<CategoriesListItem, BaseViewHolder>(
        R.layout.view_adapter_item_category_list,
        list
    ) {


    override fun convert(holder: BaseViewHolder, item: CategoriesListItem) {
        holder.setText(R.id.tvBciTitle, item.title)
        holder.setText(R.id.tvBciAuthor, item.author)
        holder.setText(R.id.tvBciDesc, item.desc)
        holder.setTextColorRes(R.id.tvBciDesc, R.color.gray3)

        setOnItemClickListener(OnItemClickListener { adapter, view, position ->
            Toast.makeText(context, "点击item成功，position:" + position, Toast.LENGTH_SHORT).show()
        })
    }


}