package com.passwordmanager.ui;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import android.widget.Button;
import android.widget.TextView;

import com.passwordmanager.R;
import com.passwordmanager.utils.Controller;
import com.passwordmanager.models.PasswordModel;
import com.passwordmanager.databinding.ActivityLoadPasswordBinding;

public class LoadPasswordActivity extends AppCompatActivity {
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityLoadPasswordBinding binding = ActivityLoadPasswordBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
  
    // Add event onclick listener
    addOnClickListenerOnButton(binding);

    // Make window fullscreen
    WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
  }
  
  // Added all the onclick event listiners
  private void addOnClickListenerOnButton(ActivityLoadPasswordBinding binding) {
    binding.loadPasswordBtn.setOnClickListener(v -> {
      String domain = binding.inputDomain.getText().toString();
      String username = binding.inputUsername.getText().toString();
      
      Controller controller = new Controller(LoadPasswordActivity.this);
      
      PasswordModel passwordmodel = controller.getPasswordByUsernameAndDomain(username, domain);
      
      if (passwordmodel == null) {
        Toast.makeText(LoadPasswordActivity.this, getString(R.string.not_found_error_message), Toast.LENGTH_SHORT).show();
      } else {
        Toast.makeText(LoadPasswordActivity.this, passwordmodel.toString(), Toast.LENGTH_LONG).show();
      }
    });
  }
}
