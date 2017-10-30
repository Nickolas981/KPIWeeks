package com.example.nickolas.kpiweeks.fragments;


import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brandongogetap.stickyheaders.StickyLayoutManager;
import com.example.nickolas.kpiweeks.App;
import com.example.nickolas.kpiweeks.R;
import com.example.nickolas.kpiweeks.di.component.AppComponent;
import com.example.nickolas.kpiweeks.di.component.DaggerPresentersComponent;
import com.example.nickolas.kpiweeks.di.module.PresentersModule;
import com.example.nickolas.kpiweeks.model.enteties.Week;
import com.example.nickolas.kpiweeks.presenters.WeekPresenter;
import com.example.nickolas.kpiweeks.utils.DayInformationUtil;
import com.example.nickolas.kpiweeks.views.WeekView;
import com.example.nickolas.kpiweeks.widgets.FragmentChanger;
import com.example.nickolas.kpiweeks.widgets.adapters.WeekAdapter;
import com.example.nickolas.kpiweeks.widgets.layout_managers.TopSnappedStickyLayoutManager;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;


public class WeekFragment extends Fragment implements WeekView {

    @Inject
    WeekPresenter presenter;
    RecyclerView recyclerView;
//    WeekAdapter weekAdapter;
    WeekAdapter weekAdapter;
    FragmentChanger fragmentChanger;
    StickyLayoutManager layoutManager;
//    LinearLayoutManager linearLayoutManager;
    public boolean scroll = false;
    int week;
    String id;

    public static WeekFragment newInstance(int c, String id, FragmentChanger fc) {
        WeekFragment weekFragment = new WeekFragment();
        weekFragment.week = c;
        weekFragment.id = id;
        weekFragment.fragmentChanger = fc;
        return weekFragment;
    }


    public AppComponent getAppComponent() {
        return ((App) getActivity().getApplication()).appComponent();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerPresentersComponent.builder()
                .appComponent(getAppComponent())
                .presentersModule(new PresentersModule())
                .build()
                .inject(this);

        presenter.setView(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_week, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        weekAdapter = new WeekAdapter(container.getContext());
//        linearLayoutManager = new LinearLayoutManager(container.getContext());
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager = new TopSnappedStickyLayoutManager(container.getContext(), weekAdapter);
//        layoutManager.elevateHeaders(true);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(weekAdapter);
        presenter.getSchedule(id, week, getActivity());

        return view;
    }

    private void scroll(){
        if (scroll) {
            int position = new DayInformationUtil()
                    .scrollTo(weekAdapter.getKeys());
            if (position == 8){
                fragmentChanger.change();
            }
            layoutManager.scrollToPosition(position*2);
            scroll = false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void showSchedule(@NotNull Week week) {
        weekAdapter.setWeek(week, this.week);
        scroll();
    }


}
