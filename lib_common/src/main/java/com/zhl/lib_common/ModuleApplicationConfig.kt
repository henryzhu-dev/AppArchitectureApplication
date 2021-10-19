package com.zhl.lib_common

/**
 *    author : zhuhl
 *    date   : 2021/10/19
 *    desc   :
 *    refer  :
 */
object ModuleApplicationConfig {

    private const val MODULE_MAIN = "com.zhl.module_main.MainApplication"
    private const val MODULE_SAMPLE = "com.zhl.module_sample.SampleApplication"
    private const val MODULE_A = "com.zhl.module_a.AApplication"

    val modules = mutableListOf<String>(MODULE_MAIN, MODULE_SAMPLE, MODULE_A)

}