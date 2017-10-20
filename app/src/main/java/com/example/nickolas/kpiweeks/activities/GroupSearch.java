package com.example.nickolas.kpiweeks.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.nickolas.kpiweeks.App;
import com.example.nickolas.kpiweeks.R;
import com.example.nickolas.kpiweeks.di.component.AppComponent;
import com.example.nickolas.kpiweeks.di.component.DaggerPresentersComponent;
import com.example.nickolas.kpiweeks.di.module.PresentersModule;
import com.example.nickolas.kpiweeks.presenters.SearchPresenter;
import com.example.nickolas.kpiweeks.utils.MyToolbar;
import com.example.nickolas.kpiweeks.views.GroupSearchView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

//import com.example.nickolas.kpiweeks.di.component.DaggerPresentersComponent;


public class GroupSearch extends AppCompatActivity implements GroupSearchView {

    private ArrayAdapter adapter;
    private ArrayList<String> group_names;
    private Map<String, String> grop_names;
    private MyToolbar myToolbar;
    @Inject
    SearchPresenter presenter;
    AutoCompleteTextView autoCompleteTextView;


//    public void showPredictions(@NonNull List<String> strings) {
//
//        group_names.clear();
//        group_names.addAll(strings);
//        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, group_names);
//        autoCompleteTextView.setAdapter(adapter);
////        adapter.notifyDataSetChanged();
//    }

    @Override
    public void showPredictions(@NonNull Map<String, String> strings) {

        grop_names.clear();
        grop_names.putAll(strings);
        List<String> keys = new ArrayList<>(grop_names.keySet());
        Collections.sort(keys);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, keys);
        autoCompleteTextView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
    }

    public AppComponent getAppComponent() {
        return ((App) getApplication()).appComponent();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_search);

        DaggerPresentersComponent.builder()
                .appComponent(getAppComponent())
                .presentersModule(new PresentersModule())
                .build()
                .inject(this);

        presenter.setView(this);
//        group_names = new ArrayList<>();
        grop_names = new HashMap<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        myToolbar = new MyToolbar(findViewById(android.R.id.content));
        autoCompleteTextView = findViewById(R.id.autocomplete);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, group_names);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.addTextChangedListener(new TextListener());
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra("name",adapter.getItem(i).toString());
                intent.putExtra("id", grop_names.get(adapter.getItem(i)));
                setResult(RESULT_OK, intent);
                finish();
//                myToolbar.getTitle().setText(grop_names.get(adapter.getItem(i)));
            }
        });
    }



    @NotNull
    @Override
    public Context getContext() {
        return this;
    }

    class TextListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            presenter.getGroups(s.toString());
        }
    }
}