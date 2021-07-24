package fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.zhl.lib_common.base.BaseListFragment
import com.zhl.lib_common.model.BookModel.BookBean
import com.zhl.lib_common.service.BookService
import com.zhl.lib_core.databinding.FragmentCommonListBinding
import com.zhl.lib_core.service.ServiceGenerator
import com.zhl.lib_core.utils.ToastUtil
import com.zhl.module_book.R
import com.zhl.module_book.adapter.CommonListAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 *    author : zhuhl
 *    date   : 2021/7/1
 *    desc   :
 *    refer  : 通用的列表Fragment
 */
class CommonListFragment : BaseListFragment<FragmentCommonListBinding, BookBean>() {

    override val swipeRefreshLayout: SwipeRefreshLayout by lazy {
        binding.swipeRefreshLayout
    }

    override val recyclerView: RecyclerView
        get() = binding.rvCommonList

    override val adapter: CommonListAdapter by lazy {
        CommonListAdapter()
    }

    override val loadDataList: (page: Int) -> Unit = {
        loadDataList(it)
    }

    override fun initOtherData() {
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
                }, { e ->
                    handleError()
                }
            )
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentCommonListBinding {
        return FragmentCommonListBinding.inflate(inflater)
    }

}