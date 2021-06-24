package com.zhl.rxjavaarchitecture.model;

import java.io.Serializable;

/**
 * author : zhuhl
 * date   : 2021/6/24
 * desc   :
 */
public class CategoriesListItem implements Serializable {

    public String categoryName;

    public int bookId;

    public String title;

    public String author;

    public String coverImg;

    public String word;

    public String desc;

    // 状态 AboutChapterStatus
    public String chapterStatus;

}
