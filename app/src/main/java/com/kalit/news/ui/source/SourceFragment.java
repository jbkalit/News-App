package com.kalit.news.ui.source;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import com.kalit.news.R;
import com.kalit.news.domain.model.Sources;
import com.kalit.news.ui.article.ArticleFragment;
import com.kalit.news.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by KALIT on 9/16/2018.
 */

public class SourceFragment extends Fragment implements SourceView {

    private SourcePresenter sourcePresenter;
    @BindView(R.id.rv_source)
    public RecyclerView recyclerView;
    @BindView(R.id.pb_loading_source)
    ProgressBar mProgressBar;
    public SourceAdapter sourceAdapter;


    public static Fragment newInstance() {
        Fragment fragment = new SourceFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news__fragment_source, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ButterKnife.bind(this, getActivity());

        sourcePresenter = new SourcePresenter(getActivity().getApplication());
        onAttachView();

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                mLinearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        sourceAdapter = new SourceAdapter(getActivity());
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setAdapter(sourceAdapter);

        sourceAdapter.setOnItemClickListener(new SourceAdapter.SourceListener() {
            @Override
            public void onNewsClicked(int position, String id) {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.ID, id);

                Fragment fragment = ArticleFragment.newInstance();
                fragment.setArguments(bundle);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_source, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttachView() {
        mProgressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        sourcePresenter.onAttach(this);
        sourcePresenter.requestNewsSources();

    }

    @Override
    public void onDetachView() {
        sourcePresenter.onDetach();
    }

    @Override
    public void onDestroy() {
        onDetachView();
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void initialized(String response) {
        mProgressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        Sources sources = (new Gson()).fromJson(response, Sources.class);
        sourceAdapter.setData(sources.getSources());
    }
}

