package com.passwordmanager;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.passwordmanager.database.user.UserHandler;
import com.passwordmanager.database.user.UserModel;
import com.passwordmanager.ui.adapter.UserAdapter;
import com.passwordmanager.utils.Permissions;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  private ArrayList<UserModel> userList;
  private Permissions permissionsHandle;
  private ListView listViewUsers;
  private UserHandler userHandler;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Make window fullscreen
    WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

    // Check and request permission when the app is first opened
    permissionsHandle = new Permissions(this);
    if (!permissionsHandle.checkPermission()) permissionsHandle.requestPermission();

    // Init FAB & ListView
    FloatingActionButton fab = findViewById(R.id.fab);
    listViewUsers = findViewById(R.id.listview);

    // Init Handlers & List of users
    userHandler = new UserHandler(this);
    userList = new ArrayList<>();

    // Fab onclick
    fab.setOnClickListener(
        (view) -> {
          // Test for add data to db
          userHandler.create(
              new UserModel("USERNAME TEST", "PASSWORD TEST", UserModel.UserType.NORMAL));
          Toast.makeText(this, getString(R.string.toast_success_add), Toast.LENGTH_SHORT).show();
          // Update listview
          update();
        });

    update();
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

  private void update() {
    // Get all data from db, then show to listview
    userList = userHandler.getAll();
    listViewUsers.setAdapter(new UserAdapter(this, userList));

    // Handle when item long click
    listViewUsers.setOnItemLongClickListener(
        (adapter, view, position, id) -> {
          // Show delete confirm dialog
          new MaterialAlertDialogBuilder(this)
              .setTitle(this.getString(R.string.dialog_title_delete))
              .setMessage(this.getString(R.string.dialog_message_delete))
              .setNegativeButton(this.getString(R.string.dialog_cancel), (dialog, which) -> {})
              .setPositiveButton(
                  this.getString(R.string.dialog_delete),
                  (dialog, which) -> {
                    userHandler.delete((int) userList.get(position).getId());
                    Toast.makeText(
                            this, this.getString(R.string.toast_success_delete), Toast.LENGTH_SHORT)
                        .show();
                    update();
                  })
              .create()
              .show();
          return true;
        });
  }
}
