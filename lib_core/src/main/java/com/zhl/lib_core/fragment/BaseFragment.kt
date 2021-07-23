package com.zhl.lib_core.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 *    author : zhuhl
 *    date   : 2021/6/24
 *    desc   :
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    var activity: Activity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as Activity
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        loadData()
    }

    abstract fun initData()

    abstract fun loadData()

    abstract fun getViewBinding(inflater: LayoutInflater, viewGroup: ViewGroup?): VB


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null;
    }

}