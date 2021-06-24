package com.zhl.rxjavaarchitecture.activity

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.zhl.baselibrary.activity.BaseActivity
import com.zhl.baselibrary.databinding.ActivityCommonListBinding
import com.zhl.baselibrary.service.ServiceGenerator
import com.zhl.baselibrary.utils.ToastUtil
import com.zhl.rxjavaarchitecture.R
import com.zhl.rxjavaarchitecture.adapter.CommonListAdapter
import com.zhl.rxjavaarchitecture.api.BookService
import com.zhl.rxjavaarchitecture.model.CategoriesListItem
import com.zhl.rxjavaarchitecture.model.CategoriesListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    override fun initListener() {

    }

    override fun loadData() {
        var bookService = ServiceGenerator.createService(BookService::class.java)
        var bookListCall = bookService.getBookList(1, 10, 1)
        bookListCall.enqueue(object : Callback<CategoriesListResponse> {
            override fun onResponse(
                call: Call<CategoriesListResponse>,
                response: Response<CategoriesListResponse>
            ) {
                Log.d("http返回：", response.body().toString() + "")
            }

            override fun onFailure(call: Call<CategoriesListResponse>, t: Throwable) {
                Log.d("http返回：",  "请求失败：" + t.message)
            }
        })
    }

    override fun getLayoutViewBinding(): ActivityCommonListBinding {
        return ActivityCommonListBinding.inflate(layoutInflater)
    }
}