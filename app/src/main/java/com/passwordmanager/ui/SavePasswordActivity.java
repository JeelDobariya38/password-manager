package com.passwordmanager.ui;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import com.passwordmanager.R;
import com.passwordmanager.utils.Controller;
import com.passwordmanager.databinding.ActivitySavePasswordBinding;

public class SavePasswordActivity extends AppCompatActivity {
  private Controller controller;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivitySavePasswordBinding binding = ActivitySavePasswordBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
  
    // Add event onclick listener
    addOnClickListenerOnButton(binding);
    
    // Make window fullscreen
    WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
  }
  
  // Added all the onclick event listiners
  private void addOnClickListenerOnButton(ActivitySavePasswordBinding binding) {
    binding.inputDomain.setOnFocusChangeListener((v, hasFocus) -> {
        if (hasFocus) {
            binding.inputDomain.setHint(R.string.placeholder_domain_field);
        } else {
            binding.inputDomain.setHint("");
        }
    });

    binding.inputUsername.setOnFocusChangeListener((v, hasFocus) -> {
        if (hasFocus) {
            binding.inputUsername.setHint(R.string.placeholder_username_field);
        } else {
            binding.inputUsername.setHint("");
        }
    });

    binding.inputPassword.setOnFocusChangeListener((v, hasFocus) -> {
        if (hasFocus) {
            binding.inputPassword.setHint(R.string.placeholder_password_field);
        } else {
            binding.inputPassword.setHint("");
        }
    });

    binding.savePasswordBtn.setOnClickListener(v -> {
      String domain = binding.inputDomain.getText().toString();
      String username = binding.inputUsername.getText().toString();
      String password = binding.inputPassword.getText().toString();
      String notes = binding.inputNotes.getText().toString();
      
      controller = new Controller(SavePasswordActivity.this);
      int res = controller.savePasswordEntity(domain, username, password, notes);
      
      if (res == -2) {
        Toast.makeText(SavePasswordActivity.this, getString(R.string.warn_fill_form), Toast.LENGTH_SHORT).show();
      } 
      else if (res == -1) {
        Toast.makeText(SavePasswordActivity.this, getString(R.string.fail_msg), Toast.LENGTH_SHORT).show();
      } else {
        Toast.makeText(SavePasswordActivity.this, getString(R.string.sucess_clause) + res, Toast.LENGTH_SHORT).show();
      }
    });
  }
}
