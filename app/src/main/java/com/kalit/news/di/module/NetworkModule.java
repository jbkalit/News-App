package com.kalit.news.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by KALIT on 9/16/2018.
 */

@Module
public class NetworkModule {
    String mBaseUrl;

    public NetworkModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit
                .Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(mBaseUrl)
                .build();
    }
}
