package com.passwordmanager.ui;

import android.os.Bundle;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import android.view.LayoutInflater;

import com.passwordmanager.R;
import com.passwordmanager.databinding.ActivityMainBinding;
// import com.passwordmanager.utils.Permissions;

public class MainActivity extends AppCompatActivity {
  
  // private Permissions permissionsHandle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
  
    // Add event onclick listener
    addOnClickListenerOnButton(binding);
    
    // Make window fullscreen
    WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
    
    /* Comment the code as permission is not need to write into app data dir.
        // Check and request permission when the app is first opened
        permissionsHandle = new Permissions(this);
        if (!permissionsHandle.checkPermission()) permissionsHandle.requestPermission();
    */
  }

  /* Comment the code as permission is not need to write into app data dir.
      @Override
      public void onRequestPermissionsResult(
          int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Permissions.PERMISSION_REQUEST_CODE) {
          if (permissionsHandle.isPermissionGranted(grantResults)) {
            // Permission has been granted
            Toast.makeText(this, getString(R.string.permission_granted), Toast.LENGTH_LONG).show();
          } else {
            // Permission not granted
            Toast.makeText(this, getString(R.string.permission_denied), Toast.LENGTH_LONG).show();
          }
        }
      }
  */
  
  // Added all the onclick event listiners
  private void addOnClickListenerOnButton(ActivityMainBinding binding) {
    binding.savePasswordBtn.setOnClickListener(v -> {
      Intent savepasswordintent = new Intent(MainActivity.this, SavePasswordActivity.class);
      startActivity(savepasswordintent);
    });
    
    binding.loadPasswordBtn.setOnClickListener(v -> {
      Intent loadpasswordintent = new Intent(MainActivity.this, LoadPasswordActivity.class);
      startActivity(loadpasswordintent);
    });
	
	binding.aboutUsBtn.setOnClickListener(v -> {
	  Intent aboutusintent = new Intent(MainActivity.this, AboutUsActivity.class);
      startActivity(aboutusintent);
    });
    
    binding.quitBtn.setOnClickListener(v -> {
      finishAndRemoveTask();
    });
  }
}
