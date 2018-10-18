package com.kalit.news.ui.article;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.kalit.news.R;
import com.kalit.news.domain.model.Articles;
import com.kalit.news.ui.webview.Webview;
import com.kalit.news.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by KALIT on 9/17/2018.
 */

public class ArticleFragment extends Fragment implements ArticleView {

    private ArticlePresenter articlePresenter;
    @BindView(R.id.rv_article)
    public RecyclerView mList;
    @BindView(R.id.pb_loading_article)
    ProgressBar mProgressBar;
    public ArticleAdapter articleAdapter;
    public Bundle bundle;
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;


    public static Fragment newInstance() {
        Fragment frag = new ArticleFragment();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news__fragment_article, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ButterKnife.bind(this, getActivity());
        setHasOptionsMenu(true);
        articlePresenter = new ArticlePresenter(getActivity().getApplication());
        onAttachView();


        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                mLinearLayoutManager.getOrientation());
        mList.addItemDecoration(dividerItemDecoration);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        articleAdapter = new ArticleAdapter(getActivity());
        mList.setLayoutManager(mLinearLayoutManager);
        mList.setAdapter(articleAdapter);

        articleAdapter.setOnItemClickListener(new ArticleAdapter.ArticleListener() {
            @Override
            public void onArticleClicked(int position, String url) {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.URL, url);

                Fragment fragment = Webview.newInstance();
                fragment.setArguments(bundle);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_article, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }

    @Override
    public void onAttachView() {
        mProgressBar.setVisibility(View.VISIBLE);
        mList.setVisibility(View.GONE);
        articlePresenter.onAttach(this);
        articlePresenter.requestArticleData(bundle.getString(Constants.ID));
    }

    @Override
    public void onDetachView() {
        articlePresenter.onDetach();
    }

    @Override
    public void initialized(String response) {
        mProgressBar.setVisibility(View.GONE);
        mList.setVisibility(View.VISIBLE);
        Articles newsItem = (new Gson()).fromJson(response, Articles.class);
        articleAdapter.setData(newsItem.getArticles());
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.news__option_menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("onQueryTextChange", newText);

                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit", query);
                    searchView.clearFocus();
                    articlePresenter.searchArticleData(bundle.getString(Constants.ID),query);
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                // Not implemented here
                return false;
            default:
                break;
        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }
}
