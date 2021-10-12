package com.zhl.lib_common.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.animation.AlphaInAnimation
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import com.zhl.lib_common.R
import com.zhl.lib_common.model.ListResp
import com.zhl.lib_core.fragment.BaseFragment

/**
 *    author : zhuhl
 *    date   : 2021/10/12
 *    desc   :
 *    refer  :
 */
abstract class BaseSmartListFragment<VB : ViewBinding, T> : BaseFragment<VB>(), OnRefreshListener,
    com.chad.library.adapter.base.listener.OnLoadMoreListener {

    abstract val smartRefreshLayout: SmartRefreshLayout
    abstract val recyclerView: RecyclerView
    abstract val adapter: BaseQuickAdapter<T, *>
    abstract val layoutManager: RecyclerView.LayoutManager

    /**
     * 当前页码
     */
    protected var pageNumber: Int = firstPage()
    protected var pageSize: Int = 10
    protected fun firstPage(): Int {
        return 1
    }

    /**
     * 是否需要自动刷新
     */
    open var needAutoRefresh = true


    abstract val loadDataList: ((page: Int) -> Unit)

    override fun initData() {
        //绑定recyclerView和Adapter
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        //设置顶部刷新header
        if (needAutoRefresh) {
            smartRefreshLayout.autoRefresh(200)//自动刷新
        }
        if (enableRefresh()) {
            smartRefreshLayout.setEnableRefresh(true)
            smartRefreshLayout.setOnRefreshListener(this)
        } else {
            smartRefreshLayout.setEnableRefresh(false)
        }

        //设置底部加载更多footer
        //设置是否可以上拉加载更多
        adapter.loadMoreModule.isEnableLoadMore = enableLoadMore()
        //设置加载中的状态
        adapter.setEmptyView(getLoadingLayout())
        //设置上拉加载监听
        if (enableLoadMore()) {
            // 是否自定加载下一页（默认为true）
            adapter.loadMoreModule.isAutoLoadMore = true
            // 预加载的位置（默认为1），当前count-5就开始加载下一页
            adapter.loadMoreModule.preLoadNumber = 5
            // 当数据不满一页时，是否继续自动加载（默认为true）
            adapter.loadMoreModule.isEnableLoadMoreIfNotFullPage = false
            // 所有数据加载完成后，是否允许点击（默认为false）
//            adapter.loadMoreModule.enableLoadMoreEndClick = true
            adapter.loadMoreModule.setOnLoadMoreListener(this)
        }
        //设置adapter显示的动画效果
        adapter.animationEnable = true
        adapter.adapterAnimation = AlphaInAnimation(0.4f)
        initOtherData()
    }


    abstract fun initOtherData()

    override fun onRefresh(refreshLayout: RefreshLayout) {
        pageNumber = firstPage()
        loadDataList.invoke(pageNumber)
    }

    override fun onLoadMore() {
        pageNumber++
        loadDataList.invoke(pageNumber)
    }

    fun handleData(listResp: ListResp<T>?) {
        if (listResp == null) {
            if (pageNumber == firstPage()) {
                smartRefreshLayout.finishRefresh(false)
                adapter.setEmptyView(getErrorLayout())
            } else {
                adapter.loadMoreModule.loadMoreFail()
            }
            return
        }
        val list = listResp.list
        pageNumber = listResp.pageNum
        if (pageNumber == firstPage() && list.isNullOrEmpty()) {
            adapter.setEmptyView(getEmptyLayout())
            return
        }
        if (pageNumber == firstPage()) {
            smartRefreshLayout.finishRefresh(true)
            adapter.setNewInstance(list)
        } else {
            adapter.addData(list)
        }
        var isLastPage = adapter.data.size >= listResp.total || list == null || list.isEmpty()

        if (isLastPage) {
            adapter.loadMoreModule.loadMoreEnd(true)
        } else {
            adapter.loadMoreModule.loadMoreComplete()
        }
    }


    /**
     * 是否可以下拉刷新，默认true表示可以
     */
    open fun enableRefresh(): Boolean {
        return true
    }

    /**
     * 是否可以上拉加载更多，默认true表示可以
     */
    open fun enableLoadMore(): Boolean {
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        smartRefreshLayout.setOnRefreshListener(null)
    }

    protected fun getLoadingLayout(): Int {
        return R.layout.layout_common_loading_view
    }

    protected fun getEmptyLayout(): View {
        val emptyLayout = View.inflate(activity, R.layout.layout_common_empty_view, null)
        emptyLayout.setOnClickListener {
            onRefresh(smartRefreshLayout)
        }
        return emptyLayout
    }

    protected fun getErrorLayout(): View {
        val errorLayout = View.inflate(activity, R.layout.layout_common_error_view, null)
        errorLayout.setOnClickListener {
            onRefresh(smartRefreshLayout)
        }
        return errorLayout
    }

}