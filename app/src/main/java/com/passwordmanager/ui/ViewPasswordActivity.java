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
import com.passwordmanager.databinding.ActivityViewPasswordBinding;

/*
  Activity expects id, domain, username, password, notes, createdat, updatedat as intent parameters.
*/

public class ViewPasswordActivity extends AppCompatActivity {
  private int passwordEnitityId = 0;
  private ActivityViewPasswordBinding binding;
  private Controller controller;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityViewPasswordBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    
    Intent intent = getIntent();
    passwordEnitityId = intent.getIntExtra("id", 1); // -1 is a invalid id.

    if (passwordEnitityId == -1) { //invalid enitity
      finish();
    }
    
    controller = new Controller(ViewPasswordActivity.this);
    
    // Filling the textviews with data
    // fillDataInTextview();
  
    // Add event onclick listener
    addOnClickListenerOnButton();
    
    // Make window fullscreen
    WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
  }
  
  private void fillDataInTextview() {
    PasswordModel passwordmodel = controller.getPasswordById(passwordEnitityId);
    
    binding.tvDomain.setText(getString(R.string.domain_prefix) + "  " + passwordmodel.getDomain());
    binding.tvUsername.setText(getString(R.string.username_prefix) + "  " + passwordmodel.getUsername());
    binding.tvPassword.setText(getString(R.string.password_prefix) + "  " + passwordmodel.getPassword());
    binding.tvNotes.setText(getString(R.string.notes_prefix) + "  " + passwordmodel.getNotes());
    binding.tvCreatedAt.setText(getString(R.string.createdat_prefix) + "  " + passwordmodel.getCreatedAt());
    binding.tvUpdatedAt.setText(getString(R.string.updatedat_prefix) + "  " + passwordmodel.getUpdatedAt());
  }
  
  // Added all the onclick event listiners
  private void addOnClickListenerOnButton() {
    binding.updatePasswordBtn.setOnClickListener(v -> {
      Intent viewpasswordintent = new Intent(ViewPasswordActivity.this, UpdatePasswordActivity.class);
      viewpasswordintent.putExtra("id", passwordEnitityId);
      startActivity(viewpasswordintent);
    });
    
    binding.deletePasswordBtn.setOnClickListener(v -> {
      AlertDialog confirmDialog =new AlertDialog.Builder(ViewPasswordActivity.this)
        .setTitle("Delete Password?")
        .setMessage("This action is irreverseable")
        .setPositiveButton("Confirm", (dialog, which) -> {
          performDeletePasswordAction();
        })
        .setNegativeButton("Discard", (dialog, which) -> {
          // Do Nothing
        })
        .create();
      
      confirmDialog.show();
    });
  }

  private void performDeletePasswordAction() {
    int res = controller.deletePassword(passwordEnitityId);
    
    if (res == 1) {
      Toast.makeText(ViewPasswordActivity.this, getString(R.string.delete_sucess_msg), Toast.LENGTH_SHORT).show();
      
      finish();
    } else {
      Toast.makeText(ViewPasswordActivity.this, getString(R.string.something_went_wrong_msg), Toast.LENGTH_SHORT).show();
    }
  }
  
  @Override
  protected void onResume() {
    super.onResume();
    fillDataInTextview();
  }
}
