package com.example.annabujak.listazakupow;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pawel.bujak on 06.03.2017.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private List<Product> productsList;
    public RecycleViewAdapter(List<Product> productsList){
        this.productsList = productsList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_viewer, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecycleViewAdapter.ViewHolder holder, int position) {
        holder.name.setText(productsList.get(position).getName());
        holder.weight.setText(Integer.toString(productsList.get(position).getCount()));
        holder.unit.setText(productsList.get(position).getUnit());
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public EditText name;
        public EditText weight;
        public EditText unit;

        public ViewHolder(View view) {

            super(view);

            name = (EditText) view.findViewById(R.id.name);
            weight = (EditText) view.findViewById(R.id.weight);
            unit = (EditText) view.findViewById(R.id.unit);

        }

    }
}
