package com.passwordmanager;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import android.widget.Button;
import android.widget.TextView;

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
    domainTextInput = findViewById(R.id.domain_text_input);
    usernameTextInput = findViewById(R.id.username_text_input);
    loadpasswordbtn = findViewById(R.id.load_button);
  }
  
  // Added all the onclick event listiners
  private void addOnClickListenerOnButton() {
    loadpasswordbtn.setOnClickListener(v -> {
        Toast.makeText(LoadPasswordActivity.this, "Hello Load Passwords", Toast.LENGTH_LONG).show();
    });
  }
}
