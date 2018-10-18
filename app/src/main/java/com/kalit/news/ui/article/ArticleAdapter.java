package com.kalit.news.ui.article;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kalit.news.R;
import com.kalit.news.domain.model.ArticleItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KALIT on 9/17/2018.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private ArticleListener articleListener;
    private List<ArticleItem> articleItems;
    private Context mContext;


    public ArticleAdapter(Context context) {
        articleItems = new ArrayList<>();
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleText, mDescriptionText;
        ImageView mImage;
        ArticleItem mArticlesItem;
        public void setArticlesItem(ArticleItem articlesItem) {
            this.mArticlesItem = articlesItem;
        }
        public ViewHolder(View v, final ArticleListener articleListener) {
            super(v);
            mImage = v.findViewById(R.id.iv_article_image);
            mTitleText = v.findViewById(R.id.tv_article_title);
            mDescriptionText = v.findViewById(R.id.tv_article_desc);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    articleListener.onArticleClicked(getAdapterPosition(), mArticlesItem.getUrl());
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news__item_article, parent, false);
        return new ViewHolder(v, articleListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        ArticleItem articlesItem = articleItems.get(position);
        viewHolder.setArticlesItem(articlesItem);
        RequestOptions options = new RequestOptions()
                .centerCrop();
        Glide.with(mContext).load(articlesItem.getUrlToImage()).apply(options).into(viewHolder.mImage);
        viewHolder.mTitleText.setText(articlesItem.getTitle());
        viewHolder.mDescriptionText.setText(articlesItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return articleItems.size();
    }

    public void setOnItemClickListener(ArticleListener listener) {
        articleListener = listener;
    }

    public void setData(List<ArticleItem> data) {
        articleItems.clear();
        articleItems.addAll(data);
        notifyDataSetChanged();
    }

    public interface ArticleListener {
        void onArticleClicked(int position, String url);
    }
}
