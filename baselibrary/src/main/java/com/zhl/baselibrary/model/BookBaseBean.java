package com.zhl.baselibrary.model;

/**
 * author : zhuhl
 * date   : 2021/6/25
 * desc   : 电子书模块的基础model
 * refer  :
 */
public class BookBaseBean<T> {

    private T data;

    private ResultBean result;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    private static class ResultBean {
        private int code = 0;
        private String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
