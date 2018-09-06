package com.dongumen.nickolas.kpiweeks.widgets.adapters.delegateAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class CompositeDelegateAdapter<T>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int FIRST_VIEW_TYPE = 0;

    protected final SparseArray<IDelegateAdapter> typeToAdapterMap;
    @NonNull
    protected final List<T> data = new ArrayList<>();

    protected CompositeDelegateAdapter(
            @NonNull SparseArray<IDelegateAdapter> typeToAdapterMap) {
        this.typeToAdapterMap = typeToAdapterMap;
    }

    @Override
    public final int getItemViewType(int position) {
        for (int i = FIRST_VIEW_TYPE; i < typeToAdapterMap.size(); i++) {
            final IDelegateAdapter delegate = typeToAdapterMap.valueAt(i);
            //noinspection unchecked
            if (delegate.isForViewType(data.get(position))) {
                return typeToAdapterMap.keyAt(i);
            }
        }
        throw new NullPointerException(
                "Can not get viewType for position " + position);
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return typeToAdapterMap.get(viewType)
                .onCreateViewHolder(parent, viewType);
    }

    @Override
    public final void onBindViewHolder(
            RecyclerView.ViewHolder holder, int position) {
        final IDelegateAdapter delegateAdapter =
                typeToAdapterMap.get(getItemViewType(position));
        if (delegateAdapter != null) {
            //noinspection unchecked
            delegateAdapter.onBindViewHolder(holder, data, position);
        } else {
            throw new NullPointerException(
                    "can not find adapter for position " + position);
        }
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        //noinspection unchecked
        typeToAdapterMap.get(holder.getItemViewType())
                .onRecycled(holder);
    }

    public void swapData(@NonNull List<T> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void addAll(@NonNull List<T> data) {
        int index = this.data.size();
        this.data.addAll(data);
        notifyItemRangeInserted(index, data.size());
    }

    public void addAllToStart(@NonNull List<T> data) {
        for (int i = data.size() - 1; i >= 0; i--) {
            addFirst(data.get(i));
        }
    }

    public void addFirst(T data) {
        this.data.add(0, data);
        notifyItemInserted(0);
    }

    public void add(T data) {
        this.data.add(data);
        notifyItemInserted(this.data.size() - 1);
    }

    public void remove(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void change(int position, T obj) {
        data.set(position, obj);
        notifyItemChanged(position);
    }

    public List<T> getData() {
        return data;
    }


    @Override
    public final int getItemCount() {
        return data.size();
    }

    public static class Builder<T> {

        private final SparseArray<IDelegateAdapter> typeToAdapterMap;
        private int count;

        public Builder() {
            typeToAdapterMap = new SparseArray<>();
        }

        public Builder<T> add(
                @NonNull IDelegateAdapter<?, ? extends T> delegateAdapter) {
            typeToAdapterMap.put(count++, delegateAdapter);
            return this;
        }

        public CompositeDelegateAdapter<T> build() {
            if (count == 0) {
                throw new IllegalArgumentException("Register at least one adapter");
            }
            return new CompositeDelegateAdapter<>(typeToAdapterMap);
        }
    }
}