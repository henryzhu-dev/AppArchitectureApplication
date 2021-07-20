package com.zhl.baselibrary.constant

/**
 *    author : zhuhl
 *    date   : 2021/6/27
 *    desc   :
 *    refer  :
 */
object ARouterConstant {

    object MAIN {
        private const val MAIN = "/main"
        const val INDEX = "${MAIN}/index"
    }


    object USER {
        private const val MAIN = "/user"
        const val INDEX = "${MAIN}/userIndex"
    }

    object WEB {
        const val WEB_PAGE = "/web/common"

        const val KEY_WEB_URL = "key_web_url"
    }

}