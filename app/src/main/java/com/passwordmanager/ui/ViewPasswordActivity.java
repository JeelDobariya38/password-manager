package com.passwordmanager.ui;

import android.os.Bundle;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import android.view.LayoutInflater;

import com.passwordmanager.R;
import com.passwordmanager.databinding.ActivityViewPasswordBinding;

/*
  Activity expects id, domain, username, password, notes, createdat, updatedat as intent parameters.
*/

public class ViewPasswordActivity extends AppCompatActivity {
  private int passwordEnitityId = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityViewPasswordBinding binding = ActivityViewPasswordBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    
    Intent intent = getIntent();
    passwordEnitityId = intent.getIntExtra("id", -1); // -1 is a invalid id.

    if (passwordEnitityId == -1) { //invalid enitity
      finish();
    }
    
    // Put value in textviews
    binding.tvDomain.setText(getString(R.string.domain_prefix) + "  " + intent.getStringExtra("domain"));
    binding.tvUsername.setText(getString(R.string.username_prefix) + "  " + intent.getStringExtra("username"));
    binding.tvPassword.setText(getString(R.string.password_prefix) + "  " + intent.getStringExtra("password"));
    binding.tvNotes.setText(getString(R.string.notes_prefix) + "  " + intent.getStringExtra("notes"));
    binding.tvCreatedAt.setText(getString(R.string.createdat_prefix) + "  " + intent.getStringExtra("createdat"));
    binding.tvUpdatedAt.setText(getString(R.string.updatedat_prefix) + "  " + intent.getStringExtra("updatedat"));
  
    // Add event onclick listener
    addOnClickListenerOnButton(binding);
    
    // Make window fullscreen
    WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
  }
  
  // Added all the onclick event listiners
  private void addOnClickListenerOnButton(ActivityViewPasswordBinding binding) {
    binding.updatePasswordBtn.setOnClickListener(v -> {
      // TODO: implement password update logic.
      Toast.makeText(ActivityViewPasswordBinding.this, "update password feature under development", Toast.LENGTH_SHORT).show();
    });
    
    binding.deletePasswordBtn.setOnClickListener(v -> {
      // TODO: implement password delete logic.
      Toast.makeText(ActivityViewPasswordBinding.this, "delete password feature under development", Toast.LENGTH_SHORT).show();
    });
  }
}
