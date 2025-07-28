package com.jeeldobariya.passcodes.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete

@Dao
interface PasswordsDao {

    @Insert
    fun insertPassword(password: Password): Long

    @Query("SELECT * FROM passwords ORDER BY id DESC")
    fun getAllPasswords(): List<Password>

    @Query("SELECT * FROM passwords WHERE id = :id")
    fun getPasswordById(id: Int): Password?

    @Query("SELECT * FROM passwords WHERE username = :username AND domain = :domain")
    fun getPasswordByUsernameAndDomain(username: String, domain: String): Password?

    @Update
    fun updatePassword(password: Password): Int

    @Query("DELETE FROM passwords WHERE id = :id")
    fun deletePasswordById(id: Int): Int

    @Delete
    fun deletePassword(password: Password): Int
}
