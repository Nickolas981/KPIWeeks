package com.example.nickolas.kpiweeks.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.nickolas.kpiweeks.views.WeekView;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;


public class WeekFragment extends Fragment implements WeekView{

    @Inject
    WeekPresenter presenter;

    public WeekFragment newInstance(){
        return new WeekFragment();
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
        presenter.getSchedule("567");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_week, container, false);
    }

    @Override
    public void showSchedule(@NotNull Week week) {

    }
}
