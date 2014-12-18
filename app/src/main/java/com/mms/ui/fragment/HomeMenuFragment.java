package com.mms.ui.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mms.vendetta.R;
import com.mms.app.AppConfiguration;
import com.mms.ui.adapter.HomeMenuAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by GrzegorzFeathers on 10/27/14.
 */
public class HomeMenuFragment extends Fragment {

    private View mRootView;
    private DrawerLayout mDrawerLayout;
    @InjectView(R.id.recycler_menu_options) RecyclerView mRecyclerMenuOptionsView;

    private RecyclerView.Adapter mRecyclerMenuOptionsAdapter;
    private RecyclerView.LayoutManager mRecyclerMenuOptionsManager;
    private ActionBarDrawerToggle mDrawerToogle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mRootView = inflater.inflate(R.layout.fragment_home_menu, container, false);

        ButterKnife.inject(this, this.mRootView);

        this.mRecyclerMenuOptionsView.setHasFixedSize(true);

        this.mRecyclerMenuOptionsManager = new LinearLayoutManager(this.getActivity());
        this.mRecyclerMenuOptionsAdapter = new HomeMenuAdapter(AppConfiguration.getMenuOptions());

        this.mRecyclerMenuOptionsView.setLayoutManager(this.mRecyclerMenuOptionsManager);
        this.mRecyclerMenuOptionsView.setAdapter(this.mRecyclerMenuOptionsAdapter);

        return this.mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.mDrawerLayout = (DrawerLayout) this.getActivity().findViewById(R.id.drawer_layout);
        this.mDrawerToogle = new ActionBarDrawerToggle(
                this.getActivity(), this.mDrawerLayout,
                R.string.app_name, R.string.app_name){
            @Override
            public void onDrawerOpened(View drawerView) {
                getActivity().supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                getActivity().supportInvalidateOptionsMenu();
            }
        };
        this.mDrawerLayout.setDrawerListener(this.mDrawerToogle);

        this.mDrawerToogle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.mDrawerToogle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(this.mDrawerToogle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
