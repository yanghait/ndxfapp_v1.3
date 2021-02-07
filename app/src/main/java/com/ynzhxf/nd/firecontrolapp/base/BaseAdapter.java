package com.ynzhxf.nd.firecontrolapp.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.ynzhxf.nd.firecontrolapp.R;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter {

    public PreferencesService preferences = new PreferencesService();

    protected Context mContext;
    private static final int EMPTYTYPE = 3, NONETWORK = 4;
    protected List<T> list = new ArrayList();
    private String emptyString = "";
    private int emptyId = 0;

    private boolean isNotWork = true;

    public List<T> getList() {
        return list;
    }

    public BaseAdapter(Context context) {
        this.mContext = context;
    }

    public BaseAdapter(Context context, String emptyString) {
        this.mContext = context;
        this.emptyString = emptyString;
    }

    public BaseAdapter(Context context, String emptyString, int emptyId) {
        this.mContext = context;
        this.emptyString = emptyString;
        this.emptyId = emptyId;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (viewType) {
            case EMPTYTYPE:
                v = getEmptyView(parent);
                EmptyViewHolder viewHolder = new EmptyViewHolder(v);
                return viewHolder;
            case NONETWORK:
                v = getNoNetWorkView(parent);
                NoNetViewHolder noNetViewHolder = new NoNetViewHolder(v);
                return noNetViewHolder;
            default:
                return createView(parent, viewType);
        }
    }

    protected View getEmptyView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.base_recycleview_empty_view, parent, false);
    }

    protected View getNoNetWorkView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.base_recycleview_no_network_view, parent, false);
    }

    protected int getEmptyImg() {
        return R.drawable.recycle_empty_icon;
    }

    protected String getEmptyText() {
        if (emptyString.equals(""))
            return mContext.getString(R.string.recycle_empty_txt);
        else
            return emptyString;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BaseAdapter.EmptyViewHolder) {
            EmptyViewHolder viewHolder = (EmptyViewHolder) holder;
            viewHolder.getEmptyTv().setText(getEmptyText());
            viewHolder.emptyIv.setImageResource(getEmptyImg());
        } else {
            if (list.size() > 0)
                convert(holder, position);
        }

    }


    protected abstract RecyclerView.ViewHolder createView(ViewGroup parent, int viewType);

    public abstract void convert(RecyclerView.ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return list.size() == 0 ? 1 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.size() < 1) {
            if (isNotWork)
                return EMPTYTYPE;
            else
                return NONETWORK;
        } else {
            return super.getItemViewType(position);
        }
    }

    public void isNoNetWork(boolean isNotWork) {
        this.isNotWork = isNotWork;
        if (isNotWork == false) {
            this.list.clear();
            this.notifyDataSetChanged();
        }
    }


    public void addData(List<T> list) {
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }


    public void update(List list) {
        this.list = list;
        this.notifyDataSetChanged();
    }


    public void remove(int positon) {
        list.remove(positon);
        notifyDataSetChanged();
    }

    private class EmptyViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout empty_parent_layout;
        private ImageView emptyIv;
        private TextView emptyTv;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            empty_parent_layout = itemView.findViewById(R.id.empty_parent_layout);
            emptyIv = itemView.findViewById(R.id.empty_iv);
            emptyTv = itemView.findViewById(R.id.empty_tv);
        }

        public RelativeLayout getEmpty_parent_layout() {
            return empty_parent_layout;
        }

        public ImageView getEmptyIv() {
            return emptyIv;
        }

        public TextView getEmptyTv() {
            return emptyTv;
        }
    }

    public class NoNetViewHolder extends RecyclerView.ViewHolder {

        public NoNetViewHolder(View itemView) {
            super(itemView);
        }
    }

}
