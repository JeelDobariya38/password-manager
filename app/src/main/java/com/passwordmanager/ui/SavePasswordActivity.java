package com.passwordmanager.ui;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import android.widget.Button;
import android.widget.TextView;

import com.passwordmanager.R;
import com.passwordmanager.utils.Controller;

public class SavePasswordActivity extends AppCompatActivity {
  private TextView domainTextInput;
  private TextView usernameTextInput;
  private TextView passwordTextInput;
  private TextView notesTextInput;
  private Button savepasswordbtn;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_savepassword);
    
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
    passwordTextInput = findViewById(R.id.input_password);
    notesTextInput = findViewById(R.id.input_notes);
    savepasswordbtn = findViewById(R.id.save_password_btn);
  }
  
  // Added all the onclick event listiners
  private void addOnClickListenerOnButton() {
    savepasswordbtn.setOnClickListener(v -> {
      String domain = domainTextInput.getText().toString();
      String username = usernameTextInput.getText().toString();
      String password = passwordTextInput.getText().toString();
      String notes = notesTextInput.getText().toString();
      
      Controller controller = new Controller(SavePasswordActivity.this);
      
      int res = controller.savePasswordEntity(domain, username, password, notes);
      
      if (res == -2) {
        Toast.makeText(SavePasswordActivity.this, "please fill the form first!!", Toast.LENGTH_SHORT).show();
      } 
      else if (res == -1) {
        Toast.makeText(SavePasswordActivity.this, "Failed: please try again!!", Toast.LENGTH_SHORT).show();
      } else {
        Toast.makeText(SavePasswordActivity.this, "Success: " + res, Toast.LENGTH_SHORT).show();
      }
    });
  }
}
