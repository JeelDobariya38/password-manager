package com.passwordmanager.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.passwordmanager.database.MyDatabaseHelper;
import com.passwordmanager.models.PasswordModel;

public class Controller {
  private MyDatabaseHelper dbHelper;

  public Controller(Context context) {
    dbHelper = new MyDatabaseHelper(context);
  }

  public int savePasswordEntity(String domain, String username, String password, String notes) {
    // Return -2 on empty parameter.
    // Return -1 on error in SQL.
    // Return +ve on success.

    if (domain.isEmpty() || username.isEmpty() || password.isEmpty()) {
      return -2;
    }

    SQLiteDatabase db = dbHelper.getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put("domain", domain);
    values.put("username", username);
    values.put("password", password);
    if (!notes.isEmpty()) values.put("notes", notes);

    long newRowId = db.insert(MyDatabaseHelper.PASSWORDS_TABLE, null, values);
    db.close();

    return newRowId == -1 ? -1 : (int) newRowId;
  }

  public PasswordModel getPasswordById(int id) {
    SQLiteDatabase db = dbHelper.getReadableDatabase();

    // Define the selection criteria
    String selection = "id = ?";
    String[] selectionArgs = new String[]{String.valueOf(id)};

    Cursor cursor = db.query(
      MyDatabaseHelper.PASSWORDS_TABLE,
      null,
      selection,
      selectionArgs,
      null, null, null);

    PasswordModel passwordModel = null;

    if (cursor != null) {
      if (cursor.moveToFirst()) {
        String domain = cursor.getString(cursor.getColumnIndex("domain"));
        String username = cursor.getString(cursor.getColumnIndex("username"));
        String password = cursor.getString(cursor.getColumnIndex("password"));
        String notes = cursor.getString(cursor.getColumnIndex("notes"));
        String createdAt = cursor.getString(cursor.getColumnIndex("createdat"));
        String updatedAt = cursor.getString(cursor.getColumnIndex("updatedat"));

        passwordModel = new PasswordModel(id, domain, username, password, notes, createdAt, updatedAt);
      }
      cursor.close();
    }

    db.close();
    return passwordModel;
  }

  public PasswordModel getPasswordByUsernameAndDomain(String username, String domain) {
    SQLiteDatabase db = dbHelper.getReadableDatabase();

    // Define the selection criteria
    String selection = "username = ? AND domain = ?";
    String[] selectionArgs = new String[]{username, domain};

    Cursor cursor = db.query(
      MyDatabaseHelper.PASSWORDS_TABLE,
      null,
      selection,
      selectionArgs,
      null,
      null,
      null
      );

    PasswordModel passwordModel = null;

    if (cursor != null) {
      if (cursor.moveToFirst()) {
        int id = cursor.getInt(cursor.getColumnIndex("id"));
        String retrievedUsername = cursor.getString(cursor.getColumnIndex("username"));
        String retrievedDomain = cursor.getString(cursor.getColumnIndex("domain"));
        String password = cursor.getString(cursor.getColumnIndex("password"));
        String notes = cursor.getString(cursor.getColumnIndex("notes"));
        String createdAt = cursor.getString(cursor.getColumnIndex("createdat"));
        String updatedAt = cursor.getString(cursor.getColumnIndex("updatedat"));

        passwordModel = new PasswordModel(id, retrievedDomain, retrievedUsername, password, notes, createdAt, updatedAt);
      }
      cursor.close();
    }

    db.close();
    return passwordModel;
  }


  public int updatePassword(int id, String domain, String username, String password, String notes) {
    // Return -2 on empty parameter.
    // Return -1 on error in SQL.
    // Return number of rows affected on success.

    if (domain.isEmpty() || username.isEmpty() || password.isEmpty()) {
      return -2;
    }

    SQLiteDatabase db = dbHelper.getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put("domain", domain);
    values.put("username", username);
    values.put("password", password);
    values.put("notes", notes);

    int rowsAffected;
    
    try {
        rowsAffected = db.update(MyDatabaseHelper.PASSWORDS_TABLE, values, "id = ?", new String[]{String.valueOf(id)});

        if (rowsAffected > 0) {
            String updateQuery = "UPDATE " + MyDatabaseHelper.PASSWORDS_TABLE + " SET updatedat = datetime('now') WHERE id = ?";
            db.execSQL(updateQuery, new Object[]{id});
        }
    } catch (Exception e) {
        db.close();
        return -1;  // Error in SQL
    }
    db.close();

    return rowsAffected == 0 ? -1 : rowsAffected;
  }

  public int deletePassword(int id) {
    // Return number of rows affected (should be 1 if the deletion is successful).

    SQLiteDatabase db = dbHelper.getWritableDatabase();
    int rowsDeleted = db.delete(MyDatabaseHelper.PASSWORDS_TABLE, "id = ?", new String[]{String.valueOf(id)});
    db.close();

    return rowsDeleted;
  }
}
