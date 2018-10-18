package com.kalit.news.ui.base;

/**
 * Created by KALIT on 9/16/2018.
 */

public interface BaseView<T extends BasePresenter> {
    void onAttachView();
    void onDetachView();
}