package com.passwordmanager;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import com.passwordmanager.utils.Permissions;

public class MainActivity extends AppCompatActivity {
  
  private Button savepasswordbtn;
  private Button loadpasswordbtn;
  private Button quitbtn;
  
  private Permissions permissionsHandle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    // Initalizing the view elememts
    savepasswordbtn = (Button)findViewById(R.id.save_password_activity_button);
    loadpasswordbtn = (Button)findViewById(R.id.load_password_activity_button);
    quitbtn = (Button)findViewById(R.id.quit_button);
    
    // Add event onclick listener
    savepasswordbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent savepasswordintent = new Intent(MainActivity.this, SavePasswordActivity.class);
            startActivity(savepasswordintent);
        }
    });
    
    loadpasswordbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // not implemented yet
            return;
        }
    });
    
    quitbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finishAndRemoveTask();
        }
    });
    
    // Make window fullscreen
    WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

    // Check and request permission when the app is first opened
    permissionsHandle = new Permissions(this);
    if (!permissionsHandle.checkPermission()) permissionsHandle.requestPermission();
  }

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
}
