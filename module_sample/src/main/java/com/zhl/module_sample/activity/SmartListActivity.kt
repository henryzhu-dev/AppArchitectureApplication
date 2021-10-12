package com.zhl.module_sample.activity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.zhl.lib_common.base.BaseSmartListActivity
import com.zhl.lib_common.constant.ARouterConstant
import com.zhl.lib_common.databinding.LayoutCommonSmartListBinding
import com.zhl.lib_common.model.BookModel
import com.zhl.lib_common.vm.getActivityVM
import com.zhl.module_sample.adapter.SampleListAdapter
import com.zhl.module_sample.vm.SampleListViewModel


/**
 *    author : zhuhl
 *    date   : 2021/10/24
 *    desc   :
 */

@Route(path = ARouterConstant.SAMPLE.SMART_LIST)
class SmartListActivity : BaseSmartListActivity<LayoutCommonSmartListBinding, BookModel>() {

    private val sampleListViewModel by getActivityVM<SampleListViewModel>()

    override val smartRefreshLayout: SmartRefreshLayout by lazy {
        binding.refreshLayout
    }
    override val recyclerView: RecyclerView by lazy {
        binding.recyclerView
    }
    override val adapter: BaseQuickAdapter<BookModel, *> = SampleListAdapter()
    override val layoutManager: RecyclerView.LayoutManager
        get() = LinearLayoutManager(this)

    override val loadDataList: (page: Int) -> Unit = {
        sampleListViewModel.getList(pageNumber, pageSize)
    }

    override fun createObserver() {
        sampleListViewModel.listLiveData.observe(this) {
            handleData(it)
        }
    }

    override fun enableRefresh(): Boolean {
        return true
    }

    override fun enableLoadMore(): Boolean {
        return true
    }


    override fun getLayoutViewBinding(): LayoutCommonSmartListBinding {
        return LayoutCommonSmartListBinding.inflate(layoutInflater)
    }
}