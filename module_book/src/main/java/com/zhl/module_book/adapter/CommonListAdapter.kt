package com.zhl.module_book.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhl.lib_common.model.BookModel.BookBean
import com.zhl.lib_core.service.ServiceGenerator
import com.zhl.lib_common.util.CommonImageLoadUtil
import com.zhl.module_book.R

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   :
 */
class CommonListAdapter() :
    BaseQuickAdapter<BookBean, BaseViewHolder>(
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


    override fun convert(holder: BaseViewHolder, item: BookBean) {
        holder.setText(R.id.tvBciTitle, item.title)
        holder.setText(R.id.tvBciAuthor, item.author)
        holder.setText(R.id.tvBciDesc, item.desc)
        holder.setTextColorRes(R.id.tvBciDesc, R.color.gray3)
        CommonImageLoadUtil.loadImageWithContext(
            context,
            holder.getView(R.id.rivBciCoverImg),
            ServiceGenerator.BASE_URL + item.coverImg
        )
    }


}