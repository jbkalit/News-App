package com.kalit.news;

import android.app.Application;

import com.kalit.news.di.component.ApplicationComponent;
import com.kalit.news.di.component.DaggerApplicationComponent;
import com.kalit.news.di.module.ApplicationModule;
import com.kalit.news.di.module.NetworkModule;
import com.kalit.news.util.Constants;

/**
 * Created by KALIT on 9/16/2018.
 */

public class NewsApp extends Application{
    private static NewsApp newInstance;
    public static NewsApp getInstance() { return newInstance; }
    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        newInstance = this;

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(Constants.SERVICE_BASE_URL))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
