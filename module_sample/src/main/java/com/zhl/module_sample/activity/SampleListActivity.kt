package com.zhl.module_sample.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alibaba.android.arouter.facade.annotation.Route
import com.zhl.lib_common.base.BaseListActivity
import com.zhl.lib_common.constant.ARouterConstant
import com.zhl.lib_common.model.BookModel
import com.zhl.lib_common.vm.getActivityVM
import com.zhl.lib_core.databinding.ActivityCommonListBinding
import com.zhl.lib_core.utils.ToastUtil
import com.zhl.module_sample.R
import com.zhl.module_sample.adapter.SampleListAdapter
import com.zhl.module_sample.vm.SampleListViewModel

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   :
 */

@Route(path = ARouterConstant.SAMPLE.SAMPLE_LIST)
class SampleListActivity : BaseListActivity<ActivityCommonListBinding, BookModel>() {

    private val TAG = SampleListActivity::class.java.simpleName


    private val sampleListViewModel by getActivityVM<SampleListViewModel>()

    override val swipeRefreshLayout: SwipeRefreshLayout by lazy {
        binding.swipeRefreshLayout
    }

    override val recyclerView: RecyclerView
        get() = binding.rvCommonList

    override val adapter: SampleListAdapter by lazy {
        SampleListAdapter()
    }

    override val loadDataList: (page: Int) -> Unit = { page ->
        loadDataList(page)
    }


    override fun createObserver() {
        sampleListViewModel.listLiveData.observe(this) {
            handleData(it)
        }
    }


    override fun initOtherData() {
        adapter.setOnItemClickListener { adapter, view, position ->
            ToastUtil.show("点击了${position}项")
        }
        adapter.addChildClickViewIds(R.id.llDetail, R.id.rivBciCoverImg)
        adapter.setOnItemChildClickListener { adapter, view, position ->
            if (view.id == R.id.llDetail) {
                ToastUtil.show("点击了查看详情")
                return@setOnItemChildClickListener
            }
            if (view.id == R.id.rivBciCoverImg) {
                ToastUtil.show("点击了头像")
                return@setOnItemChildClickListener
            }
        }
    }

    private fun loadDataList(page: Int) {
        sampleListViewModel.getList(page, 10)
    }

    override fun enableRefresh(): Boolean {
        return true
    }

    override fun enableLoadMore(): Boolean {
        return true
    }


    override fun getLayoutViewBinding(): ActivityCommonListBinding {
        return ActivityCommonListBinding.inflate(layoutInflater)
    }


    override fun isShowToolBar(): Boolean {
        return true
    }

    override fun getToolBarTitle(): String {
        return "通用标题样式"
    }

}