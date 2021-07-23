package com.zhl.lib_core.constant

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

    object BOOK {
        private const val BOOK = "/book"
        const val BOOK_LIST = "${BOOK}/list"
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