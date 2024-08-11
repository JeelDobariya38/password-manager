package com.passwordmanager;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import android.widget.Button;
import android.widget.TextView;

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
    domainTextInput = findViewById(R.id.domain_text_input);
    usernameTextInput = findViewById(R.id.username_text_input);
    passwordTextInput = findViewById(R.id.password_text_input);
    notesTextInput = findViewById(R.id.notes_text_input);
    savepasswordbtn = findViewById(R.id.save_button);
  }
  
  // Added all the onclick event listiners
  private void addOnClickListenerOnButton() {
    savepasswordbtn.setOnClickListener(v -> {
        Toast.makeText(SavePasswordActivity.this, "Hello Save Passwords", Toast.LENGTH_LONG).show();
    });
  }
}
