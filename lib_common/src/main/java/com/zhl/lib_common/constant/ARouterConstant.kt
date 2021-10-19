package com.zhl.lib_common.constant

/**
 *    author : zhuhl
 *    date   : 2021/6/27
 *    desc   : 项目所有的路由地址
 *    refer  :
 */
object ARouterConstant {

    object MAIN {
        private const val MAIN = "/main"
        const val INDEX = "$MAIN/index"
    }

    object SAMPLE {
        private const val SAMPLE = "/sample"
        const val SAMPLE_DATA_PROVIDER = "$SAMPLE/data_provider"
        const val SAMPLE_LIST = "$SAMPLE/list"
        const val SMART_LIST = "$SAMPLE/smartList"
        const val SAMPLE_DETAIL = "$SAMPLE/detail"
    }


    object A {
        private const val A = "/A"
        const val TEST = "$A/test"
    }

}