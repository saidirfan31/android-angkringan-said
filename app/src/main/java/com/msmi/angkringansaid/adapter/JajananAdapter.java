package com.msmi.angkringansaid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.msmi.angkringansaid.R;
import androidx.recyclerview.widget.RecyclerView;

import com.msmi.angkringansaid.model.JajananModel;

import java.util.ArrayList;
import java.util.List;

public class JajananAdapter extends RecyclerView.Adapter<JajananAdapter.MyViewHolder> {
    List<JajananModel> listItem;
    TextView mNama, mharga, mporsi;
    ImageView mFoto;
    View view;
    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public JajananAdapter(Context ctx) {
        this.ctx = ctx;
        listItem = new ArrayList<>();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View v) {
            super(v);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_jajanan, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        JajananModel item = listItem.get(position);
        mNama = holder.itemView.findViewById(R.id.txt_name_jajanan);
        mharga = holder.itemView.findViewById(R.id.txt_harga);
        mporsi = holder.itemView.findViewById(R.id.txt_porsi);
        mFoto = holder.itemView.findViewById(R.id.img_movie);

        mNama.setText(item.getName());
        mharga.setText(item.getharga());
        mporsi.setText(item.getporsi());
        mFoto.setImageResource(item.getimgJajanan());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public void add(JajananModel item) {
        listItem.add(item);
        notifyItemInserted(listItem.size() + 1);
    }

    public void addAll(List<JajananModel> listItem) {
        for (JajananModel item : listItem) {
            add(item);
        }
    }

    public void removeAll() {
        listItem.clear();
        notifyDataSetChanged();
    }

    public void remove(int pos) {
        listItem.remove(pos);
        notifyDataSetChanged();
    }

    public void swap(List<JajananModel> datas) {
        if (datas == null || datas.size() == 0) listItem.clear();
        if (listItem != null && listItem.size() > 0)
            listItem.clear();
        listItem.addAll(datas);
        notifyDataSetChanged();
    }

    public JajananModel getItem(int pos) {
        return listItem.get(pos);
    }

    public void setFilter(List<JajananModel> list) {
        listItem = new ArrayList<>();
        listItem.addAll(list);
        notifyDataSetChanged();
    }

    public List<JajananModel> getListItem() {
        return listItem;
    }
}
