package com.example.annabujak.listazakupow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private  LinearLayoutManager layoutManager;
    private List<Product> list;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHandler db = new DBHandler(this);
        list = db.getAllProducts();
         for(int i = 0; i < list.size(); i ++) {
             db.deleteProduct(list.get(i));
        }
        db.addProduct(new Product(0,"Gruszka",4,"kg"));
        db.addProduct(new Product(0,"Jablko",2,"kg"));
        db.addProduct(new Product(0,"Sliwki",5,"kg"));

        list = db.getAllProducts();

        rv = (RecyclerView)findViewById(R.id.recycler_view);
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        adapter = new RecycleViewAdapter(list);
        rv.setAdapter(adapter);
        }
    }
