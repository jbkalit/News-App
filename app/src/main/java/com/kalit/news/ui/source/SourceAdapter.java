package com.kalit.news.ui.source;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kalit.news.R;
import com.kalit.news.domain.model.SourceItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KALIT on 9/16/2018.
 */

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.ViewHolder> {


    private List<SourceItem> mData;
    private Context mContext;
    private SourceListener sourceListener;

    public SourceAdapter(Context context) {
        mData = new ArrayList<>();
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleText;
        TextView mDescText;
        SourceItem sourceItem;

        public void setSourceItem(SourceItem newsSourceItem) {
            this.sourceItem = newsSourceItem;
        }

        public ViewHolder(View v, final SourceListener sourceListener) {
            super(v);

            mTitleText = v.findViewById(R.id.tv_source_title);
            mDescText = v.findViewById(R.id.tv_source_desc);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sourceListener.onNewsClicked(getAdapterPosition(), sourceItem.getId());
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news__item_source, parent, false);
        return new ViewHolder(v, sourceListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        SourceItem sourceItem = mData.get(position);
        viewHolder.setSourceItem(sourceItem);
        viewHolder.mTitleText.setText(sourceItem.getName());
        viewHolder.mDescText.setText(sourceItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnItemClickListener(SourceListener listener) {
        sourceListener = listener;
    }

    public void setData(List<SourceItem> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public interface SourceListener {
        void onNewsClicked(int position, String url);
    }
}
