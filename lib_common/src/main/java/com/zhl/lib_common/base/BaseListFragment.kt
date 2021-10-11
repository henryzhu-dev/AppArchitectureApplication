package com.zhl.lib_common.base

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnLoadMoreListener
import com.zhl.lib_common.R
import com.zhl.lib_common.model.ListResp
import com.zhl.lib_core.fragment.BaseFragment

/**
 *    author : zhuhl
 *    date   : 2021/7/1
 *    desc   :
 *    refer  :
 */
abstract class BaseListFragment<VB : ViewBinding, T> : BaseFragment<VB>(),
    SwipeRefreshLayout.OnRefreshListener,
    OnLoadMoreListener {

    abstract val swipeRefreshLayout: SwipeRefreshLayout?
    abstract val recyclerView: RecyclerView
    abstract val adapter: BaseQuickAdapter<T, *>
    abstract val loadDataList: ((page: Int) -> Unit)

    /**
     * 当前数据页码
     */
    private var currentPage = 1

    override fun initData() {
        //设置是否可下拉刷新
        swipeRefreshLayout?.isEnabled = enableRefresh()
        //设置下拉刷新监听
        swipeRefreshLayout?.setOnRefreshListener(this)
        //绑定adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = this.adapter
        //设置加载中的状态
        adapter.setEmptyView(getLoadingLayout())
        //设置是否可以上拉加载更多
        adapter.loadMoreModule.isEnableLoadMore = enableLoadMore()
        //设置上拉加载监听
        adapter.loadMoreModule.setOnLoadMoreListener(this)
        initOtherData()
    }

    abstract fun initOtherData()

    override fun loadData() {
        onRefresh()
    }

    override fun onRefresh() {
        currentPage = 1
        loadDataList(currentPage)
    }

    override fun onLoadMore() {
        currentPage++
        loadDataList(currentPage)
    }

    fun handleData(listBean: ListResp<T>?) {
        if (listBean == null) {
            handleError()
            return
        }
        if (swipeRefreshLayout?.isRefreshing == true) {
            swipeRefreshLayout?.isRefreshing = false
        }
        val page = listBean.pageNum
        val list = listBean.list
        if (page == 1 && list == null || list.isEmpty()) {
            //加载第一页为空
            adapter.loadMoreModule.loadMoreComplete()
            adapter.setEmptyView(getEmptyLayout())
            return
        }
        if (page == 1) {
            adapter.setNewInstance(list)
        } else {
            adapter.addData(list)
        }
        val isLastPage = adapter.data.size >= listBean.total || list == null || list.isEmpty()
        if (isLastPage) {
            adapter.loadMoreModule.loadMoreEnd(false)
        } else {
            adapter.loadMoreModule.loadMoreComplete()
        }
    }

    fun handleError() {
        if (swipeRefreshLayout?.isRefreshing == true) {
            swipeRefreshLayout?.isRefreshing = false
        }
        if (currentPage == 1) {
            adapter.setEmptyView(getErrorLayout())
        } else {
            adapter.loadMoreModule.loadMoreFail()
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

    protected fun getLoadingLayout(): Int {
        return R.layout.layout_common_loading_view
    }

    protected fun getEmptyLayout(): Int {
        return R.layout.layout_common_empty_view
    }

    protected fun getErrorLayout(): Int {
        return R.layout.layout_common_error_view
    }

}