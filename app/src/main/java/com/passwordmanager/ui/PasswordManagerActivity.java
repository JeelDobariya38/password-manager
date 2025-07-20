package com.passwordmanager.ui;

import android.os.Bundle;
import android.content.Intent;
import android.content.Context;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import android.view.LayoutInflater;

import com.passwordmanager.R;
import com.passwordmanager.databinding.ActivityPasswordManagerBinding;

public class PasswordManagerActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityPasswordManagerBinding binding = ActivityPasswordManagerBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
  
    // Add event onclick listener
    addOnClickListenerOnButton(binding);
    
    // Make window fullscreen
    WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
  }
  
  // Added all the onclick event listiners
  private void addOnClickListenerOnButton(ActivityPasswordManagerBinding binding) {
    binding.savePasswordBtn.setOnClickListener(v -> {
        Intent savepasswordintent = new Intent(PasswordManagerActivity.this, SavePasswordActivity.class);
        startActivity(savepasswordintent);
    });
    
    binding.loadPasswordBtn.setOnClickListener(v -> {
        Intent loadpasswordintent = new Intent(PasswordManagerActivity.this, LoadPasswordActivity.class);
        startActivity(loadpasswordintent);
    });
    
    binding.securityCheckBtn.setOnClickListener(v -> {
        Toast.makeText(this, getString(R.string.future_feat_clause), Toast.LENGTH_SHORT).show();
    });
    
    binding.importPasswordBtn.setOnClickListener(v -> {
        Toast.makeText(this, getString(R.string.future_feat_clause), Toast.LENGTH_SHORT).show();
    });
    
    binding.exportPasswordBtn.setOnClickListener(v -> {
        Toast.makeText(this, getString(R.string.future_feat_clause), Toast.LENGTH_SHORT).show();

        String textToCopy = "Your data is copied to clipboard!!";

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied Text", textToCopy);

        if (clipboard != null) {
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Text copied to clipboard!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Clipboard service not available.", Toast.LENGTH_SHORT).show();
        }
    });
  }
}
