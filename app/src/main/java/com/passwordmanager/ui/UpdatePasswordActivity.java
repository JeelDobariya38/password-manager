package com.passwordmanager.ui;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Toast;
import android.app.AlertDialog;
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
      AlertDialog confirmDialog =new AlertDialog.Builder(UpdatePasswordActivity.this)
        .setTitle(R.string.update_password_dialog_title)
        .setMessage(R.string.irreverseable_dialog_desc)
        .setPositiveButton(R.string.confirm_dialog_button_text, (dialog, which) -> {
          String newDomain = binding.inputDomain.getText().toString();
          String newUsername = binding.inputUsername.getText().toString();
          String newPassword = binding.inputPassword.getText().toString();
          String newNotes = binding.inputNotes.getText().toString();

          performUpdatePasswordAction(newDomain, newUsername, newPassword, newNotes);
        })
        .setNegativeButton(R.string.discard_dialog_button_text, (dialog, which) -> {
          // Do Nothing
        })
        .create();
      
      confirmDialog.show();
    });
  }

  private void performUpdatePasswordAction(String newDomain, String newUsername, String newPassword, String newNotes) {
    int res = controller.updatePassword(passwordEnitityId, newDomain, newUsername, newPassword, newNotes);
    
    if (res == 1) {
      Toast.makeText(this, getString(R.string.update_sucess_msg), Toast.LENGTH_SHORT).show();
      
      finish();
    } else {
      Toast.makeText(this, getString(R.string.something_went_wrong_msg), Toast.LENGTH_SHORT).show();
    }
    
    finish();
  }
}
