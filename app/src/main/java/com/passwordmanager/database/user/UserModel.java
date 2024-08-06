package com.passwordmanager.database.user;

import androidx.annotation.NonNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserModel {

  private final String username; // Username on that domain
  private final String password; // Password on that domain
  private final String createAt; // Date when user was created
  private UserType type; // User type
  private long id; // Primary key

  // Constructor to initialize user model
  public UserModel(String username, String password, UserType type) {
    this.username = username;
    this.password = password;
    this.type = type;

    // Retrieve the current date and time
    LocalDateTime currentDate = LocalDateTime.now();

    // Formatting the time so that it is better seen by users
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy HH:mm:ss");
    createAt = currentDate.format(formatter);
  }

  // Constructor with id
  public UserModel(long id, String username, String password, UserType type, String createAt) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.type = type;
    this.createAt = createAt;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCreateAt() {
    return createAt;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public UserType getType() {
    return type;
  }

  public void setType(UserType type) {
    this.type = type;
  }

  // Enum for user types
  public enum UserType {
    SYS,
    ADMIN,
    NORMAL;

    @NonNull
    @Override
    public String toString() {
      return name().toLowerCase();
    }
  }
}
