package com.passwordmanager.ui;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import android.view.LayoutInflater;

import com.passwordmanager.R;
import com.passwordmanager.utils.Controller;
import com.passwordmanager.models.PasswordModel;
import com.passwordmanager.databinding.ActivityUpdatePasswordBinding;

/*
  Activity expects id as intent parameters.
*/

public class UpdatePasswordActivity extends AppCompatActivity {
  private int passwordEnitityId = 0;
  private Controller controller;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityUpdatePasswordBinding binding = ActivityUpdatePasswordBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    
    Intent intent = getIntent();
    passwordEnitityId = intent.getIntExtra("id", -1); // -1 is a invalid id.

    if (passwordEnitityId == -1) { //invalid enitity
      finish();
    }
    
    controller = new Controller(UpdatePasswordActivity.this);
    PasswordModel passwordmodel = controller.getPasswordById(passwordEnitityId);
    
    // Put value in text view & edit texts
    binding.tvId.setText(getString(R.string.id_prefix) + "  " + passwordEnitityId);
    binding.inputDomain.setText(passwordmodel.getDomain());
    binding.inputUsername.setText(passwordmodel.getUsername());
    binding.inputPassword.setText(passwordmodel.getPassword());
    binding.inputNotes.setText(passwordmodel.getNotes());
  
    // Add event onclick listener
    addOnClickListenerOnButton(binding);
    
    // Make window fullscreen
    WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
  }
  
  // Added all the onclick event listiners
  private void addOnClickListenerOnButton(ActivityUpdatePasswordBinding binding) {
    binding.updatePasswordBtn.setOnClickListener(v -> {
      // TODO: implement password update logic.
      String newDomain = binding.inputDomain.getText().toString();
      String newUsername = binding.inputUsername.getText().toString();
      String newPassword = binding.inputPassword.getText().toString();
      String newNotes = binding.inputNotes.getText().toString();
      
      int res = controller.updatePassword(passwordEnitityId, newDomain, newUsername, newPassword, newNotes);
      
      if (res == 1) {
        Toast.makeText(UpdatePasswordActivity.this, getString(R.string.update_sucess_msg), Toast.LENGTH_SHORT).show();
        
        finish();
      } else {
        Toast.makeText(UpdatePasswordActivity.this, getString(R.string.something_went_wrong_msg), Toast.LENGTH_SHORT).show();
      }
      
      finish();
    });
  }
}
