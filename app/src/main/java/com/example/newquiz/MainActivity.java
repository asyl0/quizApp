package com.example.newquiz;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.newquiz.databinding.ActivityMainBinding;
import com.example.newquiz.fragment.AccountFragment;
import com.example.newquiz.fragment.LeaderFragment;
import com.example.newquiz.fragment.category.CategoryFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration; private ActivityMainBinding binding;
    private FrameLayout main_frame; private BottomNavigationView bottomNavigationView;
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    if (menuItem.getItemId() == R.id.nav_home){
                        setFragment(new CategoryFragment());
                        return true;
                    } else if (menuItem.getItemId() == R.id.nav_leader){
                        setFragment(new LeaderFragment());
                        return true;
                    } else if (menuItem.getItemId() == R.id.nav_account){
                        setFragment(new AccountFragment());
                        return true;
                    }
                    return false;
                }
            };

    private void setFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(main_frame.getId(), fragment);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        main_frame = findViewById(R.id.main_frame);
        bottomNavigationView = findViewById(R.id.bottom_nav_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener );

        setFragment(new CategoryFragment());
    }
}