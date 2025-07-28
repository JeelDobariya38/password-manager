/*
This is a temporary workaround for learning. In a real-world Android app, you should absolutely use suspend functions and Kotlin Coroutines/Flow with Room for database operations to prevent blocking the UI thread and ensure good performance. Direct synchronous database calls on the main thread will lead to Strict mode policy violation errors and ANRs (Application Not Responding) in production.
*/

package com.jeeldobariya.passcodes.utils

import android.content.Context
import com.jeeldobariya.passcodes.database.MasterDatabase
import com.jeeldobariya.passcodes.database.Password
import com.jeeldobariya.passcodes.database.PasswordsDao
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Custom exceptions for clearer error handling
class InvalidInputException(message: String = "Input parameters cannot be blank.") : Exception(message)
class DatabaseOperationException(message: String = "A database operation error occurred.", cause: Throwable? = null) : Exception(message, cause)
class PasswordNotFoundException(message: String = "Password with the given ID was not found.") : Exception(message)


class Controller(context: Context) {
    private val passwordsDao: PasswordsDao // Use the DAO directly

    init {
        // Initialize Room database and get the DAO instance
        val db = MasterDatabase.getDatabase(context)
        passwordsDao = db.passwordsDao
    }

    /**
     * Saves a new password entity into the database.
     * Throws InvalidInputException if parameters are blank.
     * Throws DatabaseOperationException if a database error occurs.
     * @return The rowId of the newly inserted row.
     */
    fun savePasswordEntity(domain: String, username: String, password: String, notes: String): Long {
        if (domain.isBlank() || username.isBlank() || password.isBlank()) {
            throw InvalidInputException()
        }

        val currentTimestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val newPassword = Password(
            domain = domain,
            username = username,
            password = password,
            notes = notes,
            createdAt = currentTimestamp,
            updatedAt = currentTimestamp
        )

        try {
            return passwordsDao.insertPassword(newPassword) // Room's insert returns the rowId (Long)
        } catch (e: Exception) {
            e.printStackTrace()
            throw DatabaseOperationException("Error saving password.", e)
        }
    }

    /**
     * Retrieves a password entity by its ID.
     * Throws DatabaseOperationException if a database error occurs.
     * Throws PasswordNotFoundException if the password is not found.
     * @return The Password object if found.
     */
    fun getPasswordById(id: Int): Password {
        try {
            // Room DAO methods, when not suspend, execute on the current thread.
            // THIS WILL BLOCK THE MAIN THREAD IF CALLED DIRECTLY ON IT.
            return passwordsDao.getPasswordById(id) ?: throw PasswordNotFoundException("Password with ID $id not found.")
        } catch (e: PasswordNotFoundException) {
            throw e // Re-throw if it's a specific "not found" case
        } catch (e: Exception) {
            e.printStackTrace()
            throw DatabaseOperationException("Error retrieving password by ID.", e)
        }
    }

    /**
     * Retrieves a password entity by username and domain.
     * Throws DatabaseOperationException if a database error occurs.
     * Throws PasswordNotFoundException if the password is not found.
     * @return The Password object if found.
     */
    fun getPasswordByUsernameAndDomain(username: String, domain: String): Password {
        try {
            return passwordsDao.getPasswordByUsernameAndDomain(username, domain) ?: throw PasswordNotFoundException("Password for username '$username' and domain '$domain' not found.")
        } catch (e: PasswordNotFoundException) {
            throw e // Re-throw if it's a specific "not found" case
        } catch (e: Exception) {
            e.printStackTrace()
            throw DatabaseOperationException("Error retrieving password by username and domain.", e)
        }
    }

    /**
     * Retrieves all password entities from the database.
     * Throws DatabaseOperationException if a database error occurs.
     * @return A list of Password objects.
     */
    fun getAllPasswords(): List<Password> {
        try {
            // NOTE: If PasswordsDao.getAllPasswords() was Flow<List<Password>>,
            // directly calling it like this without suspend and collect would be problematic.
            // Assuming for this synchronous version, PasswordsDao.getAllPasswords() returns List<Password>
            return passwordsDao.getAllPasswords()
        } catch (e: Exception) {
            e.printStackTrace()
            throw DatabaseOperationException("Error retrieving all passwords.", e)
        }
    }

    /**
     * Updates an existing password entity.
     * Throws InvalidInputException if parameters are blank.
     * Throws DatabaseOperationException if a database error occurs.
     * Throws PasswordNotFoundException if the password to update is not found.
     * @return The number of rows affected (usually 1).
     */
    fun updatePassword(id: Int, domain: String, username: String, password: String, notes: String): Int {
        if (domain.isBlank() || username.isBlank() || password.isBlank()) {
            throw InvalidInputException()
        }

        try {
            val existingPassword = passwordsDao.getPasswordById(id) // This is also a synchronous call now
                ?: throw PasswordNotFoundException("Password with ID $id not found for update.")

            val updatedTimestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
            val passwordToUpdate = existingPassword.copy(
                domain = domain,
                username = username,
                password = password,
                notes = notes,
                updatedAt = updatedTimestamp
            )
            return passwordsDao.updatePassword(passwordToUpdate)
        } catch (e: PasswordNotFoundException) {
            throw e
        } catch (e: Exception) {
            e.printStackTrace()
            throw DatabaseOperationException("Error updating password.", e)
        }
    }

    /**
     * Deletes a password entity by its ID.
     * Throws DatabaseOperationException if a database error occurs.
     * @return The number of rows deleted (usually 1 if successful, 0 if not found).
     */
    fun deletePassword(id: Int): Int {
        try {
            return passwordsDao.deletePasswordById(id) // Use the DAO's delete by ID
        } catch (e: Exception) {
            e.printStackTrace()
            throw DatabaseOperationException("Error deleting password.", e)
        }
    }
}
