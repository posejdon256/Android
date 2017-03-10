package com.example.annabujak.listazakupow;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager layoutManager;
    private List<Product> list;
    public void fabClick(View v) {
        DBHandler db = new DBHandler(this);
        db.addProduct(new Product(0, "", 0, "kg"));
        adapter = new RecycleViewAdapter(db);
        rv.setAdapter(adapter);

    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyPreferenceActivity mf = new MyPreferenceActivity();
       // mf.startPreferenceFragment();
        DBHandler db = new DBHandler(this);
       // list = db.getAllProducts();
      //  int count = db.getProductsNumber();
      //  for(int i = 0; i < count; i ++ )
      //      db.deleteProduct(list.get(i));
        list = db.getAllProducts();

        rv = (RecyclerView) findViewById(R.id.recycler_view);
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        adapter = new RecycleViewAdapter(db);
        rv.setAdapter(adapter);

        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabClick(v);
            }
        });
    }
}

