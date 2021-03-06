package com.binder.activity.recycleview;
import com.binder.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {

    Context context;
    List<String> data;

    public RecyclerViewAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.itemNameTV.setText(data.get(position));
        holder.itemNameTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = data.get(position);
                Toast.makeText(context, "You clicked " + item, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void removeItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(String item, int position) {
        data.add(position, item);
        notifyItemInserted(position);
    }

    public List<String> getData() {
        return data;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        View container;
        TextView itemNameTV;

        public ViewHolder(View view) {
            super(view);
            container = view;
            itemNameTV = view.findViewById(R.id.itemNameTV);
        }

    }
}