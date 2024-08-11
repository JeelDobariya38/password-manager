package com.passwordmanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
  private static final String DATABASE_NAME = "master.db";
  private static final int DATABASE_VERSION = 1;

  public static final String PASSWORDS_TABLE = "passwords";

  // SQL statement to create a passwords table.
  private static final String CREATE_PASSWORDS_TABLE = "CREATE TABLE IF NOT EXISTS " + PASSWORDS_TABLE + " (" +
    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
    "domain VARCHAR(40) NOT NULL, " +
    "username VARCHAR(60) NOT NULL, " +
    "password VARCHAR(60) NOT NULL, " +
    "notes VARCHAR(100), " +
    "createdat DATE DEFAULT CURRENT_TIMESTAMP, " +
    "updatedat DATE DEFAULT CURRENT_TIMESTAMP);";


  public MyDatabaseHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    // Create passwords table.
    db.execSQL(CREATE_PASSWORDS_TABLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // not Implemented a not needed.
    return;
  }
}
