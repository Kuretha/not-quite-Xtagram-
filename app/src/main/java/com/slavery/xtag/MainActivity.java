package com.slavery.xtag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.slavery.xtag.Fragments.HomeFragment;
import com.slavery.xtag.Fragments.NotificationFragment;
import com.slavery.xtag.Fragments.ProfileFragment;
import com.slavery.xtag.Fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {

    private NavigationBarView navigationBarView;
    private Fragment selectorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationBarView = findViewById(R.id.bottom_navigation);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_home) {
                    selectorFragment = new HomeFragment();
                } else if (item.getItemId() == R.id.nav_search) {
                    selectorFragment = new SearchFragment();
                } else if (item.getItemId() == R.id.nav_add) {
                    selectorFragment = null;
                    startActivity(new Intent(MainActivity.this, PostActivity.class));
                } else if (item.getItemId() == R.id.nav_heart) {
                    selectorFragment = new NotificationFragment();
                } else if (item.getItemId() == R.id.nav_profile) {
                    selectorFragment = new ProfileFragment();
                }

                if (selectorFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectorFragment).commit();
                }
                return true;
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }
}