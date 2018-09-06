package com.dongumen.nickolas.kpiweeks.widgets.adapters.delegateAdapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class BaseDelegateAdapter
        <VH extends BaseViewHolder, T> implements IDelegateAdapter<VH, T> {

    abstract protected void onBindViewHolder(
            @NonNull View view, @NonNull T item, @NonNull VH viewHolder);

    @LayoutRes
    abstract protected int getLayoutId();

    @NonNull
    abstract protected VH createViewHolder(View parent);

    @Override
    public void onRecycled(VH holder) {
    }

    @NonNull
    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {

        final View inflatedView = LayoutInflater
                .from(parent.getContext())
                .inflate(getLayoutId(), parent, false);
        final VH holder = createViewHolder(inflatedView);
        holder.setListener((viewType1, view) -> onBindViewHolder(view, (T) viewType1, holder));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NotNull VH holder, @NotNull List<? extends T> items, int position) {
        holder.bind(items.get(position));
    }
}
