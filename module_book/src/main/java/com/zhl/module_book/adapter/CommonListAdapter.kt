package com.zhl.module_book.adapter

import android.widget.ImageView
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhl.lib_common.model.BookModel
import com.zhl.module_book.R

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   :
 */
class CommonListAdapter() :
    BaseQuickAdapter<BookModel, BaseViewHolder>(
        R.layout.view_adapter_item_category_list
    ), LoadMoreModule {

    init {
        setOnItemClickListener { adapter, view, position ->
            // TODO: 2021/7/12 item点击事件

        }
        addChildClickViewIds(R.id.tvBciTitle)
        setOnItemChildClickListener{adapter, view, position ->
            // TODO: 2021/7/12 item中的元素点击事件
            when (view.id) {
                R.id.tvBciTitle -> {

                }
                else -> {

                }
            }
        }
    }


    override fun convert(holder: BaseViewHolder, item: BookModel) {
        holder.setText(R.id.tvBciTitle, item.title)
        holder.setText(R.id.tvBciAuthor, item.author)
        holder.setText(R.id.tvBciDesc, item.desc)
        holder.setTextColorRes(R.id.tvBciDesc, R.color.gray3)
        (holder.getView(R.id.rivBciCoverImg) as ImageView).load("http://yuenov.com:15555/" + item.coverImg)
    }


}