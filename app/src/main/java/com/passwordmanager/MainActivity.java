package com.passwordmanager;

import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import com.passwordmanager.utils.Permissions;

public class MainActivity extends AppCompatActivity {

  private Permissions permissionsHandle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
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
