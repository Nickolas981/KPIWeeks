package com.dongumen.nickolas.kpiweeks.fragments;


import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.brandongogetap.stickyheaders.StickyLayoutManager;
import com.dongumen.nickolas.kpiweeks.App;
import com.dongumen.nickolas.kpiweeks.R;
import com.dongumen.nickolas.kpiweeks.di.component.AppComponent;
import com.dongumen.nickolas.kpiweeks.di.component.DaggerPresentersComponent;
import com.dongumen.nickolas.kpiweeks.di.module.PresentersModule;
import com.dongumen.nickolas.kpiweeks.model.enteties.Week;
import com.dongumen.nickolas.kpiweeks.presenters.WeekPresenter;
import com.dongumen.nickolas.kpiweeks.utils.DayInformationUtil;
import com.dongumen.nickolas.kpiweeks.views.WeekView;
import com.dongumen.nickolas.kpiweeks.widgets.FragmentChanger;
import com.dongumen.nickolas.kpiweeks.widgets.adapters.WeekAdapter;
import com.dongumen.nickolas.kpiweeks.widgets.layout_managers.TopSnappedStickyLayoutManager;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;


public class WeekFragment extends Fragment implements WeekView {

    @Inject
    WeekPresenter presenter;
    RecyclerView recyclerView;
    WeekAdapter weekAdapter;
    FragmentChanger fragmentChanger;
    StickyLayoutManager layoutManager;
    FrameLayout frameLayout;
    public boolean scroll = false;
    int week;
    String name;

    public static WeekFragment newInstance(int weekNumber, String name, FragmentChanger fc) {
        WeekFragment weekFragment = new WeekFragment();
        weekFragment.week = weekNumber;
        weekFragment.name = name;
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
        frameLayout = view.findViewById(R.id.container);
        weekAdapter = new WeekAdapter(container.getContext());
        layoutManager = new TopSnappedStickyLayoutManager(container.getContext(), weekAdapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(weekAdapter);
        presenter.getSchedule(name, week, getActivity());

        return view;
    }

    private void scroll(){
        if (scroll) {
            int position = new DayInformationUtil()
                    .scrollTo();
            if (position == 8) fragmentChanger.change();
            layoutManager.scrollToPosition(position*2);
            scroll = false;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void showSchedule(@NotNull Week week) {
        weekAdapter.setWeek(week);
        scroll();
    }


}
