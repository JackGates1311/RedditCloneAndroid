package com.example.sr2_2020_android2021_projekat;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import com.example.sr2_2020_android2021_projekat.adapters.SortByAdapter;
import com.example.sr2_2020_android2021_projekat.fragments.AdministratorManagerFragment;
import com.example.sr2_2020_android2021_projekat.fragments.CommunitiesFragment;
import com.example.sr2_2020_android2021_projekat.fragments.CommunityPostsFragment;
import com.example.sr2_2020_android2021_projekat.fragments.CreateEditCommunityFragment;
import com.example.sr2_2020_android2021_projekat.fragments.CreateEditPostFragment;
import com.example.sr2_2020_android2021_projekat.fragments.LoginFragment;
import com.example.sr2_2020_android2021_projekat.fragments.ManageAccountFragment;
import com.example.sr2_2020_android2021_projekat.fragments.PostsFragment;
import com.example.sr2_2020_android2021_projekat.fragments.RegisterFragment;
import com.example.sr2_2020_android2021_projekat.tools.DialogHelper;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;
import com.google.android.material.navigation.NavigationView;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    public NavigationView navigationView;
    public Menu menu;
    private String communityMode;
    private String postMode;
    public AutoCompleteTextView autoCompleteTextView;
    public DialogHelper dialogHelper = new DialogHelper();
    private String sortBy;
    private String sortByMode;
    private String communityNameParam = null;

    ///

    //TODO remove duplicate store data method if possible ... (LOW PRIORITY)

    private float acelVal; // Current acceleration value and gravity
    private float acelLast; // Last acceleration value and gravity
    private float shake; // Acceleration value differ from gravity

    private static final int sensitivityLevel = 9; // Lower value, more sensitive

    ///

    public MainActivity() {
        super();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        /////

        SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        sm.registerListener(sensorListener,
                sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

        acelVal = SensorManager.GRAVITY_EARTH;
        acelLast = SensorManager.GRAVITY_EARTH;
        shake = 0.00f;

        /////

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();

        Objects.requireNonNull(actionbar).setHomeAsUpIndicator(R.drawable.ic_menu);

        actionbar.setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        menu = findViewById(R.id.activityMenu);

        FragmentTransition.to(PostsFragment.newInstance("hot"), this,
                false, R.id.viewPage);

        navigationView.setNavigationItemSelectedListener(item -> {

            item.setChecked(true);

            drawerLayout.closeDrawers();

            // Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();

            if(item.getTitle().equals("Home page")) {

                // Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();

                FragmentTransition.to(PostsFragment.newInstance("hot"), MainActivity.this,
                        false, R.id.viewPage); // fix bug here

            }

            if(item.getTitle().equals("Login")) {

               // Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();

                FragmentTransition.to(LoginFragment.newInstance(), MainActivity.this,
                        true, R.id.viewPage);

                // item.setVisible(false); -- WORKS

            }

            if(item.getTitle().equals("Logout")) {

                storeDataToSharedPreferences(null, 0);

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

                MainActivity.this.menu.findItem(R.id.action_add_post).setVisible(false);

                FragmentTransition.to(PostsFragment.newInstance("hot"), MainActivity.this,
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

                FragmentTransition.to(CreateEditCommunityFragment.newInstance(communityNameParam), MainActivity.this,
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
        });


    }

    private void storeDataToSharedPreferences(String authToken, int expiresIn) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("authToken", authToken);
        editor.putInt("expiresIn", expiresIn);

        editor.apply();
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

        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        } else if (id == R.id.action_settings) {
            //TODO Implement settings menu
            return true;
        } else if (id == R.id.action_add_post) {
            postMode = "ADD";
            FragmentTransition.to(CreateEditPostFragment.newInstance(), MainActivity.this,
                    true, R.id.viewPage);
        } else if (id == R.id.action_sort) {
            dialogHelper.showDialog(this, "Sort posts by:",
                    R.layout.fragment_sort_by, () -> {

                        sortBy = autoCompleteTextView.getText().toString().
                                toLowerCase(Locale.ROOT);
                        autoCompleteTextView.setText(sortBy);

                        Fragment fragment = null;

                        if (sortByMode.equals("posts"))
                            fragment = PostsFragment.newInstance(sortBy);
                        if (sortByMode.equals("communityPosts"))
                            fragment = CommunityPostsFragment.newInstance(
                                    getCommunityNameParam(), sortBy);

                            FragmentTransition.to(fragment, MainActivity.this,
                                    false, R.id.viewPage);
                    }, () -> {
                    }, () -> {
                        SortByAdapter sortByAdapter = new SortByAdapter();

                        autoCompleteTextView = sortByAdapter.setSortByTypes(this,
                                dialogHelper.getCurrentDialog(), sortBy);

            });
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

    @Override
    protected void onResume() {
        super.onResume();

        setGroupMenuVisibility(true, true);
    }

    public void setGroupMenuVisibility(boolean sortGroupMenuVisibility,
                                       boolean addGroupMenuVisibility) {

        if (menu != null) {

            menu.setGroupVisible(R.id.sortGroup, sortGroupMenuVisibility);

            SharedPreferences preferences = PreferenceManager.
                    getDefaultSharedPreferences(this);

            menu.setGroupVisible(R.id.addGroup,
                    preferences.getString("authToken", null) != null);
        }


    }

    private final SensorEventListener sensorListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            acelLast = acelVal;

            acelVal = (float) Math.sqrt((x * x + y * y + z * z));

            float delta = acelVal - acelLast;

            shake = shake * 0.9f + delta;

            if(shake > sensitivityLevel) { // Shake sensitivity

                FragmentTransition.to(PostsFragment.newInstance("hot"),
                        MainActivity.this, false, R.id.viewPage);

                Toast.makeText(MainActivity.this,
                        "Posts are up to date now!", Toast.LENGTH_LONG).show();

            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    public void setSortByMode(String sortByMode) {
        this.sortByMode = sortByMode;
    }

    public String getCommunityNameParam() {
        return communityNameParam;
    }

    public void setCommunityNameParam(String communityNameParam) {
        this.communityNameParam = communityNameParam;
    }

    public String getPostMode() {
        return postMode;
    }

    public void setPostMode(String postMode) {
        this.postMode = postMode;
    }

    public String getCommunityMode() {
        return communityMode;
    }

    public void setCommunityMode(String communityMode) {
        this.communityMode = communityMode;
    }
}
