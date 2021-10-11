package com.zhl.module_main.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhl.lib_common.model.BookModel
import com.zhl.module_main.R
import java.util.*

/**
 *    author : zhuhl
 *    date   : 2021/10/11
 *    desc   : 最近更新小说列表
 *    refer  :
 */
class RecentUpdateBookAdapter : BaseQuickAdapter<BookModel, BaseViewHolder>(
    R.layout.list_book_recent_update_item
), LoadMoreModule {

    override fun convert(holder: BaseViewHolder, item: BookModel) {
        holder.getView<ImageView>(R.id.rivClCoverImg).load("http://pt.yuenov.com:15555" + item.coverImg)
        holder.getView<TextView>(R.id.tvClTitle).text = item.title
        holder.getView<TextView>(R.id.tvClDesc).text = item.desc
        holder.getView<TextView>(R.id.tvClTitle).text = item.title
        holder.getView<TextView>(R.id.tvClAuthor).text = item.author

        holder.getView<TextView>(R.id.tvClCategoryName).text = item.categoryName
        val lzView = holder.getView<TextView>(R.id.tvClLz)
        val wjView = holder.getView<TextView>(R.id.tvClWj)
        if (isSerialize(item.chapterStatus)) {
            lzView.visibility = View.VISIBLE
            wjView.visibility = View.GONE
        } else {
            lzView.visibility = View.GONE
            wjView.visibility = View.VISIBLE
        }
    }


    private fun isSerialize(chapterStatus: String): Boolean {
        /**
         * 连载
         */
        val SERIALIZE = "SERIALIZE"
        if (chapterStatus.lowercase(Locale.getDefault()) == SERIALIZE.lowercase(Locale.getDefault())) {
            return true
        }
        return false
    }


}