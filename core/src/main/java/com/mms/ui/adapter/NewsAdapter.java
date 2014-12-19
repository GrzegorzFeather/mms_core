package com.mms.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mms.R;
import com.mms.networking.model.MMSNewsEntry;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GrzegorzFeathers on 12/18/14.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<MMSNewsEntry> mDataSet = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_news_entry, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MMSNewsEntry entry = this.getItem(position);

        holder.mLblTitle.get().setText(entry.getTitle());
        holder.mLblContent.get().setText(entry.getContent());
        holder.mLblDate.get().setText(entry.getDate());
    }

    @Override
    public int getItemCount() {
        return this.mDataSet.size();
    }

    public MMSNewsEntry getItem(int position){
        return this.mDataSet.get(position);
    }

    public void updateContent(List<MMSNewsEntry> news){
        this.mDataSet = news;
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private WeakReference<TextView> mLblTitle;
        private WeakReference<TextView> mLblContent;
        private WeakReference<TextView> mLblDate;

        public ViewHolder(View itemView) {
            super(itemView);

            this.mLblTitle = new WeakReference<>(
                    (TextView) itemView.findViewById(R.id.lbl_title));
            this.mLblContent = new WeakReference<>(
                    (TextView) itemView.findViewById(R.id.lbl_content));
            this.mLblDate = new WeakReference<>(
                    (TextView) itemView.findViewById(R.id.lbl_date));
        }

    }

}
