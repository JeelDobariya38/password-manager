package com.passwordmanager;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
// import com.passwordmanager.utils.Permissions;

public class MainActivity extends AppCompatActivity {
  
  private Button savepasswordbtn;
  private Button loadpasswordbtn;
  private Button quitbtn;
  
  // private Permissions permissionsHandle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    // Initalizing the view elememts
    initalizeViewVariables();
    
    // Add event onclick listener
    addOnClickListenerOnButton();
    
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
  
  // Inizalize all the local view based variables
  private void initalizeViewVariables() {
    savepasswordbtn = findViewById(R.id.save_password_activity_button);
    loadpasswordbtn = findViewById(R.id.load_password_activity_button);
    quitbtn = findViewById(R.id.quit_button);
  }
  
  // Added all the onclick event listiners
  private void addOnClickListenerOnButton() {
    savepasswordbtn.setOnClickListener(v -> {
        Intent savepasswordintent = new Intent(MainActivity.this, SavePasswordActivity.class);
        startActivity(savepasswordintent);
    });
    
    loadpasswordbtn.setOnClickListener(v -> {
        Intent loadpasswordintent = new Intent(MainActivity.this, LoadPasswordActivity.class);
        startActivity(loadpasswordintent);
    });
    
    quitbtn.setOnClickListener(v -> {
        finishAndRemoveTask();
    });
  }
}
