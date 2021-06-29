package com.zhl.rxjavaarchitecture.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.zhl.baselibrary.activity.BaseActivity
import com.zhl.baselibrary.databinding.ActivityCommonListBinding
import com.zhl.baselibrary.model.BookBaseBean
import com.zhl.baselibrary.service.ServiceGenerator
import com.zhl.baselibrary.utils.LogUtil
import com.zhl.baselibrary.utils.ToastUtil
import com.zhl.rxjavaarchitecture.R
import com.zhl.rxjavaarchitecture.adapter.CommonListAdapter
import com.zhl.rxjavaarchitecture.api.BookService
import com.zhl.rxjavaarchitecture.model.BookBean
import com.zhl.rxjavaarchitecture.model.BookListBean
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   :
 */
class CommonListActivity : BaseActivity<ActivityCommonListBinding>() {

    private val adapter: CommonListAdapter by lazy {
        CommonListAdapter(mutableListOf<BookBean>())
    }

    override fun initData() {
        binding.rvCommonList.layoutManager = LinearLayoutManager(this)
        binding.rvCommonList.adapter = this.adapter
//        adapter.setNewInstance(null)
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

    override fun initListener() {

    }

    override fun loadData() {
        /*
        var bookService = ServiceGenerator.createService(BookService::class.java)
        var bookListCall = bookService.getBookList(1, 10, 1)
        bookListCall.enqueue(object : Callback<BookBaseBean<BookListBean>> {
            override fun onResponse(
                call: Call<BookBaseBean<BookListBean>>,
                bean: Response<BookBaseBean<BookListBean>>
            ) {
                Log.d("http返回：", bean.body().toString() + "")
            }

            override fun onFailure(call: Call<BookBaseBean<BookListBean>>, t: Throwable) {
                Log.d("http返回：", "请求失败：" + t.message)
            }
        })

         */
        loadData3()
    }

    val tag = "rxjava请求"

    fun loadData2() {
        var bookService = ServiceGenerator.createService(BookService::class.java)
        val bookListObservable = bookService.getBookListObservable(1, 1, 1)
        bookListObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : Observer<BookBaseBean<BookListBean>> {
                    override fun onSubscribe(d: Disposable?) {
                        LogUtil.d(tag, "onSubscribe")
                    }

                    override fun onNext(t: BookBaseBean<BookListBean>?) {
                        LogUtil.d(tag, "onNext")
                    }

                    override fun onComplete() {
                        LogUtil.d(tag, "onComplete")

                    }

                    override fun onError(e: Throwable?) {
                        LogUtil.d(tag, "onError")
                    }
                })
    }

    fun loadData3() {
        var bookService = ServiceGenerator.createService(BookService::class.java)
        val bookListObservable = bookService.getBookListObservable(1, 1, 1)
        bookListObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { t ->
                    LogUtil.d(tag, "accept:" + t.toString())
                }, { t ->
                    LogUtil.d(tag, "onError")
                }, {
                    LogUtil.d(tag, "onComplete")
                }
            )
    }

    override fun getLayoutViewBinding(): ActivityCommonListBinding {
        return ActivityCommonListBinding.inflate(layoutInflater)
    }
}