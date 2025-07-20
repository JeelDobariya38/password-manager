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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.passwordmanager.R;
import com.passwordmanager.utils.Controller;
import com.passwordmanager.models.PasswordModel;
import com.passwordmanager.databinding.ActivityPasswordManagerBinding;

import java.util.List;

public class PasswordManagerActivity extends AppCompatActivity {
  private Controller controller;

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

        controller = new Controller(PasswordManagerActivity.this);
        List<PasswordModel> passwordList = controller.getAllPasswords();

        String textToCopy = convertPasswordsToJson(passwordList);
        
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

  // New method to convert List<PasswordModel> to JSON string
    private String convertPasswordsToJson(List<PasswordModel> passwordList) {
        JSONArray jsonArray = new JSONArray();
        try {
            for (PasswordModel password : passwordList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", password.getId());
                jsonObject.put("domain", password.getDomain());
                jsonObject.put("username", password.getUsername());
                jsonObject.put("password", password.getPassword()); // !!! Highly Sensitive Data !!!
                jsonObject.put("notes", password.getNotes());
                jsonObject.put("createdAt", password.getCreatedAt());
                jsonObject.put("updatedAt", password.getUpdatedAt());
                jsonArray.put(jsonObject);
            }
            // Move the return statement INSIDE the try block,
            // so it's covered by the catch for JSONException
            return jsonArray.toString(4);
        } catch (JSONException e) {
            e.printStackTrace(); // Log the error for debugging
            return null; // Return null or throw a custom exception
        }
    }
}
