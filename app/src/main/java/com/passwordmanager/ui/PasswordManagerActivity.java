package com.passwordmanager.ui;

import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.widget.Toast;
import androidx.annotation.Nullable;
import android.content.Intent;
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
import java.io.OutputStream;

public class PasswordManagerActivity extends AppCompatActivity {
  private Controller controller;
  private static final int CREATE_EXPORT_DATA_FILE_REQUEST = 1;

  private String exportPasswordsContent;

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
        controller = new Controller(PasswordManagerActivity.this);
        List<PasswordModel> allPasswords = controller.getAllPasswords();
        exportPasswordsContent = convertPasswordsToJson(allPasswords);

        if (exportPasswordsContent != null) {
            createFile(null);
        } else {
            Toast.makeText(this, "Failed to generate secure report content.", Toast.LENGTH_SHORT).show();
        }
    });
  }
  
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
            
        return jsonArray.toString(4);
    } catch (JSONException e) {
        return null;
    }
  }

  private void createFile(@Nullable Uri pickerInitialUri) {
    Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
    intent.addCategory(Intent.CATEGORY_OPENABLE);
    intent.setType("application/json");
    intent.putExtra(Intent.EXTRA_TITLE, "password_manager_data.json");

    if (pickerInitialUri != null) {
        intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri);
    }

    startActivityForResult(intent, CREATE_EXPORT_DATA_FILE_REQUEST);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == CREATE_EXPORT_DATA_FILE_REQUEST) {
        if (resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            if (uri != null && exportPasswordsContent != null) {
                try {
                    OutputStream outputStream = getContentResolver().openOutputStream(uri);
                    if (outputStream != null) {
                        outputStream.write(exportPasswordsContent.getBytes());
                        outputStream.close();
                        Toast.makeText(this, "Data export successfully to: " + uri.getPath(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Error export data: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Failed to get file URI or report content is empty.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "File creation cancelled.", Toast.LENGTH_SHORT).show();
        }
    }
 }
}
