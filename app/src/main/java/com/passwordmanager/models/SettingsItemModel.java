package com.passwordmanager.models;

import android.graphics.drawable.Drawable;

public class SettingsItemModel {

  private final String title;
  private final Drawable icon;

  public SettingsItemModel(String title, Drawable icon) {
    this.title = title;
    this.icon = icon;
  }
}
