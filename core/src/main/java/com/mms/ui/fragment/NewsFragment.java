package com.mms.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mms.R;
import com.mms.app.AppConfiguration;
import com.mms.app.UserMenu;
import com.mms.networking.MMSResponseHandler;
import com.mms.networking.model.MMSNews;
import com.mms.networking.request.GetNewsRequest;
import com.mms.ui.adapter.NewsAdapter;
import com.mms.util.MMSUtils;

import java.util.List;

/**
 * Created by GrzegorzFeathers on 12/18/14.
 */
public class NewsFragment extends MenuOptionFragment {

    private static final String TAG = NewsFragment.class.getSimpleName();

    private View mRootView;
    private RecyclerView mRecyclerNewsView;
    private RecyclerView.Adapter mRecyclerNewsAdapter;
    private RecyclerView.LayoutManager mRecyclerNewsManager;

    public NewsFragment(){
        this.mRecyclerNewsAdapter = new NewsAdapter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GetNewsRequest().executeAsync(new MMSResponseHandler<MMSNews>() {
            @Override
            public void onSuccess(MMSNews response) {
                ((NewsAdapter) mRecyclerNewsAdapter).updateContent(response);
            }

            @Override
            public void onFailure(List<String> errors) {
                MMSUtils.notifyErrors(getActivity(), errors);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mRootView = inflater.inflate(R.layout.fragment_news, container, false);

        this.mRecyclerNewsView = (RecyclerView) this.mRootView.findViewById(R.id.recycler_news);
        this.mRecyclerNewsView.setHasFixedSize(true);

        this.mRecyclerNewsManager = new LinearLayoutManager(this.getActivity());

        this.mRecyclerNewsView.setLayoutManager(this.mRecyclerNewsManager);
        this.mRecyclerNewsView.setAdapter(this.mRecyclerNewsAdapter);

        return this.mRootView;
    }

    @Override
    protected AppConfiguration.HomeMenuOption getMenuOption() {
        return UserMenu.NEWS;
    }

}
