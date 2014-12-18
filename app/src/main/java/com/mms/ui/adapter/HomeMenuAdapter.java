package com.mms.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mms.vendetta.R;
import com.mms.app.AppConfiguration;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

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
        holder.mImgIcon.setImageResource(option.getIconRes());
        holder.mLblTitle.setText(option.getTitleRes());
    }

    @Override
    public int getItemCount() {
        return this.mDataSet.size();
    }

    public AppConfiguration.HomeMenuOption getItem(int position){
        return this.mDataSet.get(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.img_icon) ImageView mImgIcon;
        @InjectView(R.id.lbl_title) TextView mLblTitle;

        public ViewHolder(View rootView) {
            super(rootView);
            ButterKnife.inject(this, rootView);
        }
    }

}
