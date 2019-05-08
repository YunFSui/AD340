package com.example.hw4;

import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import android.support.v4.widget.DrawerLayout;


public class HostActivity extends AppCompatActivity
                    implements HomeFragment.OnNextClickListener,
                        NavigationView.OnNavigationItemSelectedListener {

    final static String TAG = "From HostActivity - ";
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this, drawer, toolbar,
                        R.string.nav_open_drawer, R.string.nav_close_drawer);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        HomeFragment fragment = new HomeFragment();
        fragmentTransaction.add(R.id.fragment_host, fragment);
        fragmentTransaction.commit();

        navigationView = (NavigationView)findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void swapFragments(Fragment fragment) {
        Fragment newFragment = null;

        if (fragment instanceof HomeFragment) {
            newFragment = new AboutFragment();
        } else {
            newFragment = null;
        }

        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_host, newFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    @Override
    public void OnHomeFragmentNextClick(HomeFragment fragment){
        swapFragments(fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {

            case R.id.action_settings:
                Log.i(TAG, "Setting Clicked");
                Toast toast = Toast.makeText(this,
                         "Setting Clicked", Toast.LENGTH_SHORT);
                toast.show();
                return true;


            case R.id.action_share:
                Log.i(TAG, "Share Clicked");
                Toast toast1 = Toast.makeText(this,
                        "Share Clicked", Toast.LENGTH_SHORT);
                toast1.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem){

        Fragment newFragment;

        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                newFragment = new HomeFragment();
                break;

            case R.id.nav_about:
                newFragment = new AboutFragment();
                break;

            default:
                newFragment = null;
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_host, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        return false;
    }

}
