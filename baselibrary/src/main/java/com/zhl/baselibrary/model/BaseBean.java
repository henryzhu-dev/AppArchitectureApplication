package com.zhl.baselibrary.model;

/**
 * author : zhuhl
 * date   : 2021/6/25
 * desc   :
 * refer  :
 */
public class BaseBean {

    public BaseBean(String content) {
        this.content = content;
    }

    private Long baseId;

    private String content;

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return this.content;
    }
}
