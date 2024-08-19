package com.passwordmanager.ui;

import android.os.Bundle;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import android.view.LayoutInflater;

import com.passwordmanager.R;
import com.passwordmanager.databinding.ActivityAboutUsBinding;
// import com.passwordmanager.utils.Permissions;

public class AboutUsActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityAboutUsBinding binding = ActivityAboutUsBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
  
    // Add event onclick listener
    addOnClickListenerOnButton(binding);
    
    // Make window fullscreen
    WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
  }
  
  // Added all the onclick event listiners
  private void addOnClickListenerOnButton(ActivityAboutUsBinding binding) {
    binding.viewSecurityGuidelinesBtn.setOnClickListener(v -> {
	    // TODO: open security guidelines
	});
	
	binding.viewChangeLogBtn.setOnClickListener(v -> {
		// TODO: open changelog
	});
	
	binding.viewLicenseBtn.setOnClickListener(v -> {
        // TODO: open license
    });
  }
}