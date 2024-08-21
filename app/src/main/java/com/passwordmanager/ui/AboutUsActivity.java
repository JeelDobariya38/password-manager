package com.passwordmanager.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import com.passwordmanager.databinding.ActivityAboutUsBinding;
// import com.passwordmanager.utils.Permissions;

public class AboutUsActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityAboutUsBinding binding = ActivityAboutUsBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    // Add event onclick listener
    addOnClickListenerOnButton(binding);

    // Make window fullscreen
    WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
  }

  private void openBrowser(String url) {
    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    startActivity(browserIntent);
  }

  // Added all the onclick event listiners
  private void addOnClickListenerOnButton(ActivityAboutUsBinding binding) {
    binding.viewSecurityGuidelinesBtn.setOnClickListener(
        v -> {
          // TODO: open security guidelines
        });

    binding.viewChangeLogBtn.setOnClickListener(
        v -> {
          openBrowser("https://github.com/JeelDobariya38/password-manager/blob/main/changelog.md");
        });

    binding.viewLicenseBtn.setOnClickListener(
        v -> {
          openBrowser("https://github.com/JeelDobariya38/password-manager/blob/main/LICENSE.txt");
        });
  }
}
