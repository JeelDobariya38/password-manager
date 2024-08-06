package com.passwordmanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class SQLDataHelper extends SQLiteOpenHelper {

  // Database config
  private static final String DATABASE_NAME = "master.db";
  private static final int DATABASE_VERSION = 1;

  // Query table
  private final String queryTable;

  public SQLDataHelper(@Nullable Context context, String queryTable) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
    this.queryTable = queryTable;
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(queryTable);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + queryTable);
    onCreate(db);
  }
}
