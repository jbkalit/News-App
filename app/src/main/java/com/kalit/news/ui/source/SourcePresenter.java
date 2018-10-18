package com.kalit.news.ui.source;

import android.app.Application;
import android.util.Log;

import com.kalit.news.NewsApp;
import com.kalit.news.data.network.ApiService;
import com.kalit.news.ui.base.BasePresenter;
import com.kalit.news.util.Constants;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by KALIT on 9/16/2018.
 */

public class SourcePresenter implements BasePresenter<SourceView> {

    private SourceView sourceView;

    @Inject
    Retrofit retrofit;

    public SourcePresenter(Application application) {
        ((NewsApp) application).getApplicationComponent().inject(this);
    }

    @Override
    public void onAttach(final SourceView view) {
        sourceView = view;
    }
    @Override
    public void onDetach() {
        sourceView = null;
    }
    public void requestNewsSources() {
        getData();
    }
    private void getData() {
        Call<String> request = retrofit.create(ApiService.class).sourcesData(Constants.API_KEY);

        request.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.v("onResponse",response.body());
                if(sourceView != null){
                    sourceView.initialized(response.body());
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.v("onFailure", t.getLocalizedMessage());
            }
        });
    }
}
