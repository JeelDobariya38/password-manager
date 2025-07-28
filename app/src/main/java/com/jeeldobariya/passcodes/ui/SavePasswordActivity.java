package com.jeeldobariya.passcodes.ui;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import com.jeeldobariya.passcodes.R;
import com.jeeldobariya.passcodes.utils.Controller;
import com.jeeldobariya.passcodes.databinding.ActivitySavePasswordBinding;

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
