package com.mms.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mms.app.AppConfiguration;
import com.mms.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GrzegorzFeathers on 12/17/14.
 */
public class HomeMenuAdapter extends RecyclerView.Adapter<HomeMenuAdapter.ViewHolder> {

    private List<AppConfiguration.HomeMenuOption> mDataSet;

    public HomeMenuAdapter(AppConfiguration.HomeMenuOption[] menuOptions){
        this.updateContent(menuOptions);
    }

    private void updateContent(AppConfiguration.HomeMenuOption[] fullMenuOptions){
        List<AppConfiguration.HomeMenuOption> reducedMenuOptions = new ArrayList<>();
        for(AppConfiguration.HomeMenuOption o : fullMenuOptions){
            if(o.isVisible()) { reducedMenuOptions.add(o);  }
        }
        this.mDataSet = reducedMenuOptions;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_home_option, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AppConfiguration.HomeMenuOption option = this.getItem(position);
        holder.mImgIcon.get().setImageResource(option.getIconRes());
        holder.mLblTitle.get().setText(option.getTitleRes());
    }

    @Override
    public int getItemCount() {
        return this.mDataSet.size();
    }

    public AppConfiguration.HomeMenuOption getItem(int position){
        return this.mDataSet.get(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private WeakReference<ImageView> mImgIcon;
        private WeakReference<TextView> mLblTitle;

        public ViewHolder(View rootView) {
            super(rootView);
            this.mImgIcon = new WeakReference<ImageView>(
                    (ImageView) rootView.findViewById(R.id.img_icon));
            this.mLblTitle = new WeakReference<TextView>(
                    (TextView) rootView.findViewById(R.id.lbl_title));
        }
    }

}
