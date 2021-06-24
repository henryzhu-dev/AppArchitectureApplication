package com.zhl.rxjavaarchitecture.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemLongClickListener
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhl.baselibrary.utils.CommonImageLoadUtil
import com.zhl.baselibrary.utils.ToastUtil
import com.zhl.rxjavaarchitecture.R
import com.zhl.rxjavaarchitecture.model.CategoriesListItem

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
        CommonImageLoadUtil.loadImageWithContext(
            context,
            holder.getView(R.id.rivBciCoverImg),
            item.coverImg
        )
        setOnItemClickListener { adapter, view, position ->
            ToastUtil.show("点击item成功，position:$position")
        }
        setOnItemLongClickListener(OnItemLongClickListener { adapter, view, position ->
            ToastUtil.show("长按item成功，position:$position")
            return@OnItemLongClickListener true
        })
    }


}