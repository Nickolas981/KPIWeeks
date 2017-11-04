package com.dongumen.nickolas.kpiweeks.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.dongumen.nickolas.kpiweeks.App;
import com.dongumen.nickolas.kpiweeks.R;
import com.dongumen.nickolas.kpiweeks.di.component.AppComponent;
import com.dongumen.nickolas.kpiweeks.di.component.DaggerPresentersComponent;
import com.dongumen.nickolas.kpiweeks.di.module.AppModule;
import com.dongumen.nickolas.kpiweeks.di.module.PresentersModule;
import com.dongumen.nickolas.kpiweeks.presenters.SearchPresenter;
import com.dongumen.nickolas.kpiweeks.views.GroupSearchView;
import com.dongumen.nickolas.kpiweeks.widgets.listeners.TextListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class GroupSearch extends AppCompatActivity implements GroupSearchView {

    private ArrayAdapter<String> adapter;
    private List<String> group_names;
    @Inject
    SearchPresenter presenter;
    AutoCompleteTextView autoCompleteTextView;




    @Override
    public void showPredictions(@NonNull List<String> strings) {
        group_names.clear();
        group_names.addAll(strings);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, group_names);
        autoCompleteTextView.setAdapter(adapter);
    }

    public AppComponent getAppComponent() {
        return App.appComponent();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_search);

        DaggerPresentersComponent.builder()
                .appComponent(getAppComponent())
                .presentersModule(new PresentersModule())
                .appModule(new AppModule(getContext()))
                .build()
                .inject(this);

        presenter.setView(this);
        group_names = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        autoCompleteTextView = findViewById(R.id.autocomplete);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.addTextChangedListener(new TextListener(presenter));
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent();
            intent.putExtra("name", adapter.getItem(i));
            setResult(RESULT_OK, intent);
            finish();
        });
    }


    @NotNull
    @Override
    public Context getContext() {
        return this;
    }


}