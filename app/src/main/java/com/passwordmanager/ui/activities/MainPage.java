package com.passwordmanager.ui.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.passwordmanager.R;
import com.passwordmanager.ui.fragments.GeneratorFragment;
import com.passwordmanager.ui.fragments.PasswordFragment;
import com.passwordmanager.ui.fragments.SettingsFragment;

public class MainPage extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_page);

    BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
    bottomNavigationView.setOnItemSelectedListener(
        item -> {
          Fragment selectedFragment = null;

          if (item.getItemId() == R.id.nav_password) {
            selectedFragment = new PasswordFragment();
          } else if (item.getItemId() == R.id.nav_generator) {
            selectedFragment = new GeneratorFragment();
          } else if (item.getItemId() == R.id.nav_settings) {
            selectedFragment = new SettingsFragment();
          }

          if (selectedFragment != null) {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .commit();
            return true;
          }
          return false;
        });

    // Set default fragment
    if (savedInstanceState == null) {
      getSupportFragmentManager()
          .beginTransaction()
          .replace(R.id.fragment_container, new PasswordFragment())
          .commit();
    }
  }
}
