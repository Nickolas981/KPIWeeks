package com.example.nickolas.kpiweeks.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nickolas.kpiweeks.App;
import com.example.nickolas.kpiweeks.R;
import com.example.nickolas.kpiweeks.di.component.AppComponent;
import com.example.nickolas.kpiweeks.di.component.DaggerPresentersComponent;
import com.example.nickolas.kpiweeks.di.module.PresentersModule;
import com.example.nickolas.kpiweeks.model.enteties.Week;
import com.example.nickolas.kpiweeks.presenters.WeekPresenter;
import com.example.nickolas.kpiweeks.utils.WeekAdapter;
import com.example.nickolas.kpiweeks.views.WeekView;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WeekFragment extends Fragment implements WeekView{

    @Inject
    WeekPresenter presenter;
//    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    WeekAdapter weekAdapter;
    int v;
    String id;

    public static WeekFragment newInstance(int c, String id){
        WeekFragment weekFragment = new WeekFragment();
        weekFragment.v = c;
        weekFragment.id = id;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_week, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        weekAdapter = new WeekAdapter(container.getContext());
        recyclerView.setAdapter(weekAdapter);
        presenter.getSchedule(id, v);

        return view;
    }

    @Override
    public void showSchedule(@NotNull Week week) {
        weekAdapter.setWeek(week);
    }
}
