package com.kalit.news.ui.base;

/**
 * Created by KALIT on 9/16/2018.
 */

public interface BasePresenter<T extends BaseView>  {
    void onAttach(T view);
    void onDetach();
}
