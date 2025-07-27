package com.jeeldobariya.passcodes.ui;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import com.jeeldobariya.passcodes.R;
import com.jeeldobariya.passcodes.utils.Controller;
import com.jeeldobariya.passcodes.models.PasswordModel;
import com.jeeldobariya.passcodes.databinding.ActivityLoadPasswordBinding;
import com.jeeldobariya.passcodes.ui.adapter.PasswordAdapter;

import java.util.List;

public class LoadPasswordActivity extends AppCompatActivity {
  private ActivityLoadPasswordBinding binding;
  private PasswordAdapter passwordAdapter;
  private Controller controller;
    
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityLoadPasswordBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    
    // Add event onclick listener
    addOnClickListenerOnButton();

    // Make window fullscreen
    WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
  }
  
  private void fillPasswordList() {
    controller = new Controller(LoadPasswordActivity.this);
    
    List<PasswordModel> passwordList = controller.getAllPasswords();
    passwordAdapter = new PasswordAdapter(this, passwordList);
    binding.passwordList.setAdapter(passwordAdapter);
  }
  
  // Added all the onclick event listiners
  private void addOnClickListenerOnButton() {
    binding.passwordList.setOnItemClickListener((parent, view, position, id) -> {
      PasswordModel selectedPassword = (PasswordModel) passwordAdapter.getItem(position);

      // Do something with the selectedPassword
      Intent intent = new Intent(LoadPasswordActivity.this, ViewPasswordActivity.class);
      intent.putExtra("id", selectedPassword.getId());
      startActivity(intent);
    });
  }
  
  @Override
  protected void onResume() {
    super.onResume();
    fillPasswordList();
  }
}
