package com.ebartmedia;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import com.ebartmedia.Model.Categories;
import com.ebartmedia.Retrofit.IMyAPI;
import com.ebartmedia.Retrofit.RetrofitClient;
import com.mindorks.placeholderview.ExpandablePlaceHolderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
//import java.util.function.Consumer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    IMyAPI iMyAPI;
//    RecyclerView recycler_categories;


    private Map<String,List<Categories>> categoriesMap;

    private List<Categories> categoriesList;

    private ExpandablePlaceHolderView expandablePlaceHolderView;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        categoriesMap = new HashMap<>();
        categoriesList = new ArrayList<>();

        expandablePlaceHolderView = (ExpandablePlaceHolderView) findViewById(R.id.expandablePlaceHolder);

        Retrofit retrofit = RetrofitClient.getInstance();

        iMyAPI = retrofit.create(IMyAPI.class);

        fetchData();

        expandablePlaceHolderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Toast.makeText(getApplicationContext(),"Clixcked", view.getId()).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void fetchData() {

        compositeDisposable.add(iMyAPI.getCategories()
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Categories>>() {


                    @Override
                    public void accept(List<Categories> categories) throws Exception {

                        displayData(categories);

                    }
                }));

    }

    private void displayData(List<Categories> categoriesList) {


        for (Categories categories : categoriesList) {

            List<Categories> categoriesList1 = categoriesMap.get(categories.getCatName());

            if (categoriesList1 == null) {

                categoriesList1 = new ArrayList<>();
            }

            categoriesList1.add(categories);
            categoriesMap.put(categories.getCatName(), categoriesList1);
        }

        Log.d("categoriesMap", "categoriesMap" + categoriesMap.toString());

        Iterator it = categoriesMap.entrySet().iterator();

        while (it.hasNext()) {

            Map.Entry pair = (Map.Entry) it.next();
            Log.d("Key", pair.getKey().toString());

            expandablePlaceHolderView.addView(new HeaderView(this, pair.getKey().toString()));
            List<Categories> categoriesList1 = (List<Categories>) pair.getValue();

            Log.d("categoriesList1","categoriesList1" + categoriesList1);


            for (Categories categories : categoriesList1){
                expandablePlaceHolderView.addView(new ChildView(this, categories));
            }


            it.remove();
        }
    }
}
