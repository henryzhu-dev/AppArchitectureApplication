package com.zhl.module_main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 *    author : zhuhl
 *    date   : 2021/8/5
 *    desc   :
 *    refer  :
 */
class TestViewModel : ViewModel() {

    fun test() {
        viewModelScope.launch {  }
    }
}