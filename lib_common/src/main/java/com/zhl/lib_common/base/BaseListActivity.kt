package com.zhl.lib_common.base

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnLoadMoreListener
import com.zhl.lib_common.R
import com.zhl.lib_common.model.ListResp
import com.zhl.lib_core.activity.BaseActivity

/**
 *    author : zhuhl
 *    date   : 2021/6/30
 *    desc   : 通用的列表Activity，使用SwipeRefreshLayout去实现的基类
 *    refer  :
 */
abstract class BaseListActivity<VB : ViewBinding, T> : BaseActivity<VB>(),
    SwipeRefreshLayout.OnRefreshListener,
    OnLoadMoreListener {

    abstract val swipeRefreshLayout: SwipeRefreshLayout?
    abstract val recyclerView: RecyclerView
    abstract val adapter: BaseQuickAdapter<T, *>

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
        //设置是否可下拉刷新
        swipeRefreshLayout?.isEnabled = enableRefresh()
        //设置下拉刷新监听
        swipeRefreshLayout?.setOnRefreshListener(this)
        //绑定adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = this.adapter
        //设置加载中的状态
        adapter.setEmptyView(getLoadingLayout())
        //设置是否可以上拉加载更多
        adapter.loadMoreModule.isEnableLoadMore = enableLoadMore()
        //设置上拉加载监听
        if (enableLoadMore()) {
            adapter.loadMoreModule.setOnLoadMoreListener(this)
        }
        initOtherData()
    }

    abstract fun initOtherData()

    override fun loadData() {
        if (needAutoRefresh) {
            onRefresh()
        }
    }

    override fun initListener() {

    }

    override fun onRefresh() {
        pageNumber = 1
        loadDataList(pageNumber)
    }

    override fun onLoadMore() {
        pageNumber++
        loadDataList(pageNumber)
    }

    fun handleData(listResp: ListResp<T>?) {
        if (listResp == null) {
            handleError()
            return
        }
        if (swipeRefreshLayout?.isRefreshing == true) {
            swipeRefreshLayout?.isRefreshing = false
        }
        val page = listResp.pageNum
        val list = listResp.list
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
        val isLastPage = adapter.data.size >= listResp.total || list == null || list.isEmpty()
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
        if (pageNumber == 1) {
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

    protected fun getEmptyLayout(): View {
        val emptyLayout = View.inflate(this, R.layout.layout_common_empty_view, null)
        emptyLayout.setOnClickListener {
            onRefresh()
        }
        return emptyLayout
    }

    protected fun getErrorLayout(): View {
        val errorLayout = View.inflate(this, R.layout.layout_common_error_view, null)
        errorLayout.setOnClickListener {
            onRefresh()
        }
        return errorLayout
    }
}