package com.jeeldobariya.passcodes.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Permissions {

  public static final int PERMISSION_REQUEST_CODE = 100;
  private final Activity activity;

  public Permissions(Activity activity) {
    this.activity = activity;
  }

  // Check if permission has been granted
  public boolean checkPermission() {
    int resultWrite = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    int resultRead = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
    return resultWrite == PackageManager.PERMISSION_GRANTED && resultRead == PackageManager.PERMISSION_GRANTED;
  }

  // Request write and read file permissions
  public void requestPermission() {
    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
  }

  // Check if permission has been granted after the request
  public boolean isPermissionGranted(@NonNull int[] grantResults) {
    return grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED;
  }
}
