package com.example.sr2_2020_android2021_projekat;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.sr2_2020_android2021_projekat.fragments.LoginFragment;

import com.example.sr2_2020_android2021_projekat.fragments.PostDetailsFragment;
import com.example.sr2_2020_android2021_projekat.fragments.PostsFragment;
import com.example.sr2_2020_android2021_projekat.fragments.RegisterFragment;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    Menu menu;

    public MainActivity() {
        super();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();

        Objects.requireNonNull(actionbar).setHomeAsUpIndicator(R.drawable.ic_menu);

        actionbar.setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);

        FragmentTransition.to(PostsFragment.newInstance(), this,
                false, R.id.viewPage);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                item.setChecked(true);

                drawerLayout.closeDrawers();

                // Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();

                if(item.getTitle().equals("Home page")) {

                    // Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();

                    FragmentTransition.to(PostsFragment.newInstance(), MainActivity.this,
                            false, R.id.viewPage); // fix bug here

                }

                if(item.getTitle().equals("Login")) {

                   // Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();

                    FragmentTransition.to(LoginFragment.newInstance(), MainActivity.this,
                            false, R.id.viewPage);

                }

                if(item.getTitle().equals("Register")) {

                    // Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();

                    FragmentTransition.to(RegisterFragment.newInstance(), MainActivity.this,
                            false, R.id.viewPage);

                }

                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        this.menu = menu;

        getMenuInflater().inflate(R.menu.menu_main, menu);

        menu.setGroupVisible(R.id.sortGroup, true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {

            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void setSortGroupMenuVisibility(boolean sortGroupMenuVisibility,
                                           boolean addGroupMenuVisibility) {

        if (menu != null) {

            menu.setGroupVisible(R.id.sortGroup, sortGroupMenuVisibility);
            menu.setGroupVisible(R.id.addGroup, addGroupMenuVisibility);
        }


    }
}
