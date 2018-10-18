package com.kalit.news.di.component;

import com.kalit.news.di.module.ApplicationModule;
import com.kalit.news.di.module.NetworkModule;
import com.kalit.news.ui.article.ArticleFragment;
import com.kalit.news.ui.article.ArticlePresenter;
import com.kalit.news.ui.source.SourcePresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by KALIT on 9/16/2018.
 */

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {
    void inject(SourcePresenter sourcePresenter);
    void inject(ArticlePresenter articlePresenter);
}
