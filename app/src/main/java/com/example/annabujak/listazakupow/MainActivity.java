package com.example.annabujak.listazakupow;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebViewFragment;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager layoutManager;
    private FrameLayout frame;
    private List<Product> list;
    private SharedPreferences settings;
    private ConstraintLayout mainContainer;
    public void fabClick(View v) {
        DBHandler db = new DBHandler(this);
        db.addProduct(new Product(0, "", 0, "kg"));
        adapter = new RecycleViewAdapter(db);
        rv.setAdapter(adapter);

    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // mf.startPreferenceFragment();
        DBHandler db = new DBHandler(this);
        list = db.getAllProducts();



        FragmentSettings f1 = new FragmentSettings();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentSettings, f1); // f1_container is your FrameLayout container
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();

        frame = (FrameLayout) findViewById(R.id.fragmentSettings);
        mainContainer = (ConstraintLayout)findViewById(R.id.mainContaier);
        frame.setVisibility(View.INVISIBLE);

        rv = (RecyclerView) findViewById(R.id.recycler_view);
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        adapter = new RecycleViewAdapter(db);
        rv.setAdapter(adapter);
        settings = PreferenceManager.getDefaultSharedPreferences(this);

        //in the method getString "date" represents the date from the key value from step 2 and "31/12/2011" represents a default value if the key doesn't exist
        String s = settings.getString("kolor","1");
        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabClick(v);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_name) {

            frame.setVisibility(View.VISIBLE);
            mainContainer.setVisibility(View.INVISIBLE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
       String s = settings.getString("kolor","1");
        if(new String("1").equals(s)){
            LinearLayout rL = (LinearLayout) findViewById(R.id.setColor);
            rL.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else if(new String("2").equals(s)){
            LinearLayout rL = (LinearLayout) findViewById(R.id.setColor);
            rL.setBackgroundColor(Color.parseColor("#3F51B5"));
        }
        frame.setVisibility(View.INVISIBLE);
        mainContainer.setVisibility(View.VISIBLE);
        super.onResume();
    }

}

