package com.passwordmanager.database.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import androidx.annotation.Nullable;
import com.passwordmanager.database.SQLDataHelper;
import java.util.ArrayList;

public class UserHandler {

  // Table & Column name
  public static final String TABLE_NAME = "users";
  public static final String COLUMN_ID = BaseColumns._ID;
  public static final String COLUMN_USERNAME = "username";
  public static final String COLUMN_PASSWORD = "password";
  public static final String COLUMN_TYPE = "type";
  public static final String COLUMN_CREATED_AT = "createdat";

  private final SQLDataHelper sql;

  // Constructor
  public UserHandler(@Nullable Context context) {
    sql =
        new SQLDataHelper(
            context,
            "CREATE TABLE "
                + TABLE_NAME
                + " ("
                + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME
                + " VARCHAR(40) NOT NULL, "
                + COLUMN_PASSWORD
                + " VARCHAR(60) NOT NULL, "
                + COLUMN_TYPE
                + " VARCHAR(60) CHECK(type IN ('sys', 'admin', 'normal')), "
                + COLUMN_CREATED_AT
                + " DATE);");
  }

  // Create a new user
  public void create(UserModel user) {
    SQLiteDatabase db = sql.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(COLUMN_USERNAME, user.getUsername());
    values.put(COLUMN_PASSWORD, user.getPassword());
    values.put(COLUMN_TYPE, user.getType().toString());
    values.put(COLUMN_CREATED_AT, user.getCreateAt());
    long newRowId = db.insert(TABLE_NAME, null, values);
    user.setId(newRowId);
    db.close();
  }

  // Read a user by ID
  public UserModel read(int id) {
    SQLiteDatabase db = sql.getReadableDatabase();
    Cursor cursor =
        db.query(
            TABLE_NAME,
            new String[] {
              COLUMN_ID, COLUMN_USERNAME, COLUMN_PASSWORD, COLUMN_TYPE, COLUMN_CREATED_AT
            },
            COLUMN_ID + "=?",
            new String[] {String.valueOf(id)},
            null,
            null,
            null,
            null);

    if (cursor != null) {
      cursor.moveToFirst();
      UserModel user =
          new UserModel(
              cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
              cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAME)),
              cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD)),
              UserModel.UserType.valueOf(
                  cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TYPE)).toUpperCase()),
              cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CREATED_AT)));
      cursor.close();
      db.close();
      return user;
    } else {
      db.close();
      return null;
    }
  }

  // Update a user
  public int update(UserModel user) {
    SQLiteDatabase db = sql.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(COLUMN_USERNAME, user.getUsername());
    values.put(COLUMN_PASSWORD, user.getPassword());
    values.put(COLUMN_TYPE, user.getType().toString());
    values.put(COLUMN_CREATED_AT, user.getCreateAt());
    int count =
        db.update(
            TABLE_NAME, values, COLUMN_ID + " = ?", new String[] {String.valueOf(user.getId())});
    db.close();
    return count;
  }

  // Delete a user by ID
  public void delete(int id) {
    SQLiteDatabase db = sql.getWritableDatabase();
    int count = db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] {String.valueOf(id)});
    db.close();
  }

  // Get all users
  public ArrayList<UserModel> getAll() {
    ArrayList<UserModel> userList = new ArrayList<>();
    SQLiteDatabase db = sql.getReadableDatabase();
    Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

    if (cursor.moveToFirst()) {
      do {
        UserModel user =
            new UserModel(
                cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD)),
                UserModel.UserType.valueOf(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TYPE)).toUpperCase()),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CREATED_AT)));
        userList.add(user);
      } while (cursor.moveToNext());
    }

    cursor.close();
    db.close();
    return userList;
  }
}
