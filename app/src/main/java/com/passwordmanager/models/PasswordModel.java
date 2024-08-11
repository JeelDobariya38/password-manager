package com.passwordmanager.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PasswordModel {

    private int id;           // Primary key of passwords table.
    private String domain;    // Domain associated with password entity.
    private String username;  // Username associated with password entity.
    private String password;  // Password of password entity.
    private String notes;     // Notes associated with the password entity.
    private String createdAt; // Date of password entity when the entity was first saved.
    private String updatedAt; // Date of password entity when the entity was last updated.

    // Constructor to initialize a new password model without an ID (e.g., when creating a new entry)
    public PasswordModel(String domain, String username, String password, String notes) {
        this.domain = domain;
        this.username = username;
        this.password = password;
        this.notes = notes;

        // Retrieve the current date and time
        LocalDateTime currentDate = LocalDateTime.now();

        // Formatting the time so that it is better seen by users
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.createdAt = currentDate.format(formatter);
        this.updatedAt = currentDate.format(formatter);
    }

    // Constructor with ID (e.g., when fetching from the database)
    public PasswordModel(int id, String domain, String username, String password, String notes, String createdAt, String updatedAt) {
        this.id = id;
        this.domain = domain;
        this.username = username;
        this.password = password;
        this.notes = notes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "PasswordModel{" +
                "id=" + id +
                ", domain='" + domain + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", notes='" + notes + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
