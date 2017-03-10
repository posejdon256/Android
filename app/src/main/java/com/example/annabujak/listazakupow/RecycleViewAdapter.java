package com.example.annabujak.listazakupow;

import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by pawel.bujak on 06.03.2017.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private static DBHandler productsList;
    private static RecycleViewAdapter adapter;
    public RecycleViewAdapter(DBHandler productsList){
        this.productsList = productsList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_viewer, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        adapter = this;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecycleViewAdapter.ViewHolder holder, int position) {
        List<Product> list =  productsList.getAllProducts();
        holder.name.setText(list.get(position).getName());
        holder.weight.setText(Integer.toString(list.get(position).getCount()));
        holder.unit.setText(list.get(position).getUnit());
        holder.id.setText(Integer.toString(list.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return productsList.getProductsNumber();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public EditText name;
        public EditText weight;
        public EditText unit;
        public TextView id;
        public ImageButton imB;

        public ViewHolder(View view) {

            super(view);

            name = (EditText) view.findViewById(R.id.name);
            weight = (EditText) view.findViewById(R.id.weight);
            unit = (EditText) view.findViewById(R.id.unit);
            id = (TextView) view.findViewById(R.id.tvId);
            imB = (ImageButton) view.findViewById(R.id.delButton);

            name.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(id.getText().toString().equals(""))
                        return;
                    Product p = productsList.getProduct(Integer.parseInt(id.getText().toString()));
                    p.setName(name.getText().toString());
                    productsList.updateProduct(p);
                }
            });
            weight.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(id.getText().toString().equals("") || !Helper.tryParseInt(weight.getText().toString()))
                        return;
                    Product p = productsList.getProduct(Integer.parseInt(id.getText().toString()));
                    p.setCount(Integer.parseInt(weight.getText().toString()));
                    productsList.updateProduct(p);
                }
            });
            unit.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(id.getText().toString().equals(""))
                        return;
                    Product p = productsList.getProduct(Integer.parseInt(id.getText().toString()));
                    p.setUnit(unit.getText().toString());
                    productsList.updateProduct(p);
                }
            });
            imB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(id.getText().toString().equals(""))
                        return;
                    Product p = productsList.getProduct(Integer.parseInt(id.getText().toString()));
                    List<Product> products = productsList.getAllProducts();
                    for(int i = 0; i < products.size(); i ++)
                        if(products.get(i).getId() == p.getId()){
                            productsList.deleteProduct(p);
                            adapter.notifyItemRemoved(i);
                            return;
                        }

                }
            });
        }

    }
}
