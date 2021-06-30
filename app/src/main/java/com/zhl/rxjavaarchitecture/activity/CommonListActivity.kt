package com.zhl.rxjavaarchitecture.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.zhl.baselibrary.activity.BaseRVActivity
import com.zhl.baselibrary.databinding.ActivityCommonListBinding
import com.zhl.baselibrary.service.ServiceGenerator
import com.zhl.baselibrary.utils.ToastUtil
import com.zhl.rxjavaarchitecture.R
import com.zhl.rxjavaarchitecture.adapter.CommonListAdapter
import com.zhl.rxjavaarchitecture.api.BookService
import com.zhl.rxjavaarchitecture.model.BookBean
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   :
 */
class CommonListActivity : BaseRVActivity<ActivityCommonListBinding, BookBean>() {

    private val TAG = CommonListActivity::class.java.simpleName

    override val swipeRefreshLayout: SwipeRefreshLayout by lazy {
        binding.swipeRefreshLayout
    }

    override val recyclerView: RecyclerView
        get() = binding.rvCommonList

    override val adapter: CommonListAdapter by lazy {
        CommonListAdapter(mutableListOf<BookBean>())
    }

    override val loadDataList: (page: Int) -> Unit = {
        loadDataList(it)
    }

    override fun initData() {
        super.initData()
        adapter.addChildClickViewIds(R.id.llDetail, R.id.rivBciCoverImg)
        adapter.setOnItemChildClickListener(OnItemChildClickListener() { adapter, view, position ->
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

    private fun loadDataList(page: Int) {
        var bookService = ServiceGenerator.createService(BookService::class.java)
        val bookListObservable = bookService.getBookListObservable(1, pageNum = page)
        bookListObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { t ->
                    handleData(page, t.data)
                }, { t ->
                    handleError()
                }
            )
    }

    override fun enableRefresh(): Boolean {
        return true
    }

    override fun enableLoadMore(): Boolean {
        return false
    }



    override fun getLayoutViewBinding(): ActivityCommonListBinding {
        return ActivityCommonListBinding.inflate(layoutInflater)
    }
}