package com.zhl.module_main.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.zhl.lib_common.base.BaseListFragment
import com.zhl.lib_common.model.BookModel
import com.zhl.module_main.adapter.RecentUpdateBookAdapter
import com.zhl.module_main.databinding.FragmentHomeBinding
import com.zhl.module_main.vm.HomeViewModel

/**
 *    author : zhuhl
 *    date   : 2021/10/9
 *    desc   : Home页
 *    refer  :
 */
class HomeFragment : BaseListFragment<FragmentHomeBinding, BookModel>() {

    private val homeViewModel: HomeViewModel by lazy {
        HomeViewModel()
    }

    override val swipeRefreshLayout: SwipeRefreshLayout by lazy {
        binding.swipeRefreshLayout
    }

    override val recyclerView: RecyclerView
        get() = binding.rvRecentUpdate


    override val adapter: BaseQuickAdapter<BookModel, *> by lazy {
        RecentUpdateBookAdapter()
    }

    override val loadDataList: (page: Int) -> Unit = { page ->
        loadDataList(page)
    }

    override fun initOtherData() {
        homeViewModel.homeListLiveData.observe(this) {
            handleData(it)
        }
    }

    private fun loadDataList(pageNum: Int) {
        homeViewModel.getHomeList(pageNum)
    }

    override fun enableRefresh(): Boolean {
        return true
    }

    override fun enableLoadMore(): Boolean {
        return false
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, viewGroup, false)
    }


}