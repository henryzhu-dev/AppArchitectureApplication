package com.zhl.lib_common.vm

import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.zhl.lib_core.BaseApplication

/**
 *    author : zhuhl
 *    date   : 2021/8/27
 *    desc   : viewModel获取
 *    refer  :
 */


/**
 * 在Activity中得到Application上下文的ViewModel
 *
 * @return
 */
public inline fun <reified VM : BaseVM> ComponentActivity.getAppVM(): VM {
    (this.application as BaseApplication).let {
        if (it == null) {
            throw NullPointerException()
        } else {
            return it.getAppViewModelProvider().get(VM::class.java)
        }
    }
}


/**
 * 在Fragment中得到Application上下文的ViewModel
 *
 * @param VM
 * @return
 */
public inline fun <reified VM : BaseVM> Fragment.getAppVM(): VM {
    (this.requireActivity().application as BaseApplication).let {
        if (it == null) {
            throw NullPointerException()
        } else {
            return it.getAppViewModelProvider().get(VM::class.java)
        }
    }
}


/**
 * Activity中获取Activity的ViewModel
 *
 * @param VM
 * @param factoryProducer
 * @return
 */
@MainThread
public inline fun <reified VM : ViewModel> ComponentActivity.getActivityVM(
    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<VM> {
    val factoryPromise = factoryProducer ?: {
        defaultViewModelProviderFactory
    }

    return ViewModelLazy(VM::class, { viewModelStore }, factoryPromise)
}

/**
 * Fragment中获取Fragment的ViewModel
 *
 * @param ownerProducer
 * @param factoryProducer
 * @return
 */
@MainThread
public inline fun <reified VM : ViewModel> Fragment.getFragmentVM(
    noinline ownerProducer: () -> ViewModelStoreOwner = { this },
    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<VM> = createViewModelLazy(VM::class, { ownerProducer().viewModelStore }, factoryProducer)


/**
 * Fragment中获取Activity的ViewModel
 *
 * @param factoryProducer
 * @return
 */
@MainThread
public inline fun <reified VM : ViewModel> Fragment.getActivityVM(
    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<VM> = createViewModelLazy(
    VM::class,
    { requireActivity().viewModelStore },
    factoryProducer ?: { requireActivity().defaultViewModelProviderFactory }
)
