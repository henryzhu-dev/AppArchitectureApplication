package com.zhl.rxjavaarchitecture.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.zhl.baselibrary.activity.BaseActivity
import com.zhl.baselibrary.databinding.ActivityCommonListBinding
import com.zhl.baselibrary.utils.ToastUtil
import com.zhl.rxjavaarchitecture.R
import com.zhl.rxjavaarchitecture.adapter.CommonListAdapter
import com.zhl.rxjavaarchitecture.model.CategoriesListItem

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   :
 */
class CommonListActivity : BaseActivity<ActivityCommonListBinding>() {

    private val adapter: CommonListAdapter by lazy {
        CommonListAdapter(mutableListOf<CategoriesListItem>())
    }

    override fun initData() {
        binding.rvCommonList.layoutManager = LinearLayoutManager(this)
        binding.rvCommonList.adapter = this.adapter
//        adapter.setNewInstance(null)
        adapter.addChildClickViewIds(R.id.llDetail, R.id.rivBciCoverImg)
        adapter.setOnItemChildClickListener(OnItemChildClickListener(){
            adapter, view, position ->
            if (view.id == R.id.llDetail) {
                ToastUtil.show("点击了查看详情")
                return@OnItemChildClickListener
            }
            if (view.id == R.id.rivBciCoverImg) {
                ToastUtil.show("点击了头像")
                return@OnItemChildClickListener
            }
        })
    }

    override fun getLayoutViewBinding(): ActivityCommonListBinding {
        return ActivityCommonListBinding.inflate(layoutInflater)
    }
}