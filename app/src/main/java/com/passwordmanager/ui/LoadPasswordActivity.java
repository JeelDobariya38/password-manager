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

public class LoadPasswordActivity extends AppCompatActivity {
  private TextView domainTextInput;
  private TextView usernameTextInput;
  private Button loadpasswordbtn;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_loadpassword);
    
    // Initalizing the view elememts
    initalizeViewVariables();
    
    // Add event onclick listener
    addOnClickListenerOnButton();

    // Make window fullscreen
    WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
  }
  
  // Inizalize all the local view based variables
  private void initalizeViewVariables() {
    domainTextInput = findViewById(R.id.input_domain);
    usernameTextInput = findViewById(R.id.input_username);
    loadpasswordbtn = findViewById(R.id.load_password_btn);
  }
  
  // Added all the onclick event listiners
  private void addOnClickListenerOnButton() {
    loadpasswordbtn.setOnClickListener(v -> {
      String domain = domainTextInput.getText().toString();
      String username = usernameTextInput.getText().toString();
      
      Controller controller = new Controller(LoadPasswordActivity.this);
      
      PasswordModel passwordmodel = controller.getPasswordByUsernameAndDomain(username, domain);
      
      if (passwordmodel == null) {
        Toast.makeText(LoadPasswordActivity.this, "404: Not Found!!", Toast.LENGTH_SHORT).show();
      } else {
        Toast.makeText(LoadPasswordActivity.this, passwordmodel.toString(), Toast.LENGTH_LONG).show();
      }
    });
  }
}
