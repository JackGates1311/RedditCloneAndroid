package com.example.sr2_2020_android2021_projekat;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.sr2_2020_android2021_projekat.fragments.AdministratorManagerFragment;
import com.example.sr2_2020_android2021_projekat.fragments.CommunitiesFragment;
import com.example.sr2_2020_android2021_projekat.fragments.CreateEditCommunityFragment;
import com.example.sr2_2020_android2021_projekat.fragments.CreateEditPostFragment;
import com.example.sr2_2020_android2021_projekat.fragments.LoginFragment;

import com.example.sr2_2020_android2021_projekat.fragments.ManageAccountFragment;
import com.example.sr2_2020_android2021_projekat.fragments.PostsFragment;
import com.example.sr2_2020_android2021_projekat.fragments.RegisterFragment;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    public NavigationView navigationView;

    Menu menu;

    public String communityMode;

    public String postMode;

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

        navigationView = (NavigationView) findViewById(R.id.navigation_view);

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
                            true, R.id.viewPage);

                    // item.setVisible(false); -- WORKS

                }

                if(item.getTitle().equals("Logout")) {

                    // Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();

                    MainActivity.this.navigationView.getMenu().
                            findItem(R.id.navigation_bar_item_user_register).setVisible(true);

                    MainActivity.this.navigationView.getMenu().
                            findItem(R.id.navigation_bar_item_user_login).setVisible(true);

                    MainActivity.this.navigationView.getMenu().
                            findItem(R.id.navigation_bar_item_user_logout).setVisible(false);

                    MainActivity.this.navigationView.getMenu().
                            findItem(R.id.navigation_bar_item_user_manage).setVisible(false);

                    MainActivity.this.navigationView.getMenu().
                            findItem(R.id.navigation_bar_item_create_community).setVisible(false);

                    MainActivity.this.navigationView.getMenu().
                            findItem(R.id.navigation_bar_item_communities).setVisible(false);

                    MainActivity.this.navigationView.getMenu().
                            findItem(R.id.navigation_bar_item_administrator_tools).setVisible(false);

                    FragmentTransition.to(PostsFragment.newInstance(), MainActivity.this,
                            false, R.id.viewPage);


                    // item.setVisible(false); -- WORKS

                }

                if(item.getTitle().equals("Register")) {

                    // Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();

                    FragmentTransition.to(RegisterFragment.newInstance(), MainActivity.this,
                            true, R.id.viewPage);

                }

                if(item.getTitle().equals("Manage account")) {

                    // Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();

                    FragmentTransition.to(ManageAccountFragment.newInstance(), MainActivity.this,
                            true, R.id.viewPage);

                }

                if(item.getTitle().equals("Create community")) {

                    // Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();

                    communityMode = "ADD";

                    FragmentTransition.to(CreateEditCommunityFragment.newInstance(), MainActivity.this,
                            true, R.id.viewPage);

                }

                if(item.getTitle().equals("Communities")) {

                    // Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();

                    communityMode = "EDIT";

                    FragmentTransition.to(CommunitiesFragment.newInstance(), MainActivity.this,
                            true, R.id.viewPage);

                }

                if(item.getTitle().equals("Administrator tools")) {

                    FragmentTransition.to(AdministratorManagerFragment.newInstance(),
                            MainActivity.this, true, R.id.viewPage);

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
                //TODO Implement settings menu
                return true;

            case R.id.action_add_post:
                postMode = "ADD";
                FragmentTransition.to(CreateEditPostFragment.newInstance(), MainActivity.this,
                        true, R.id.viewPage);

            case R.id.action_sort:

                // Not implemented yet
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

    public void setGroupMenuVisibility(boolean sortGroupMenuVisibility,
                                           boolean addGroupMenuVisibility) {

        if (menu != null) {

            menu.setGroupVisible(R.id.sortGroup, sortGroupMenuVisibility);
            menu.setGroupVisible(R.id.addGroup, addGroupMenuVisibility);
        }


    }

}
