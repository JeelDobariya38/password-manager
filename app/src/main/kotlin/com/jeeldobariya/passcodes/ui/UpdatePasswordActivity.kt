package com.jeeldobariya.passcodes.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.jeeldobariya.passcodes.R
import com.jeeldobariya.passcodes.database.Password // Use your Room entity directly
import com.jeeldobariya.passcodes.databinding.ActivityUpdatePasswordBinding
import com.jeeldobariya.passcodes.utils.Controller
import com.jeeldobariya.passcodes.utils.DatabaseOperationException
import com.jeeldobariya.passcodes.utils.InvalidInputException
import com.jeeldobariya.passcodes.utils.PasswordNotFoundException

/*
 * Activity expects id as intent parameters.
 */
class UpdatePasswordActivity : AppCompatActivity() {

    private var passwordEntityId: Int = 0 // Using Kotlin property declaration
    private lateinit var controller: Controller // Use lateinit since initialized in onCreate
    private lateinit var binding: ActivityUpdatePasswordBinding // Use lateinit for binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdatePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        passwordEntityId = intent.getIntExtra("id", -1) // -1 is an invalid id.

        if (passwordEntityId == -1) { // invalid entity
            Toast.makeText(this, getString(R.string.error_invalid_password_id), Toast.LENGTH_SHORT).show()
            finish()
            return // Exit onCreate if ID is invalid
        }

        controller = Controller(this)

        // Fill data into views
        fillDataInViews()

        // Add event onclick listener
        addOnClickListenerOnButton()

        // Make window fullscreen
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    private fun fillDataInViews() {
        try {
            val passwordEntity: Password = controller.getPasswordById(passwordEntityId)

            binding.tvId.text = "${getString(R.string.id_prefix)}  $passwordEntityId"
            binding.inputDomain.setText(passwordEntity.domain)
            binding.inputUsername.setText(passwordEntity.username)
            binding.inputPassword.setText(passwordEntity.password)
            binding.inputNotes.setText(passwordEntity.notes)

        } catch (e: PasswordNotFoundException) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            finish() // Close activity if password isn't found
        } catch (e: DatabaseOperationException) {
            Toast.makeText(this, "${getString(R.string.something_went_wrong_msg)}: ${e.message}", Toast.LENGTH_LONG).show()
            e.printStackTrace()
            finish() // Close activity on database error
        } catch (e: Exception) {
            Toast.makeText(this, "${getString(R.string.something_went_wrong_msg)}: ${e.message}", Toast.LENGTH_LONG).show()
            e.printStackTrace()
            finish()
        }
    }

    // Added all the onclick event listeners
    private fun addOnClickListenerOnButton() {
        binding.updatePasswordBtn.setOnClickListener {
            val newDomain = binding.inputDomain.text.toString()
            val newUsername = binding.inputUsername.text.toString()
            val newPassword = binding.inputPassword.text.toString()
            val newNotes = binding.inputNotes.text.toString()

            try {
                // Call the controller method which now throws exceptions
                val rowsAffected = controller.updatePassword(passwordEntityId, newDomain, newUsername, newPassword, newNotes)

                if (rowsAffected > 0) { // Room's update returns count of rows affected
                    Toast.makeText(this, getString(R.string.update_sucess_msg), Toast.LENGTH_SHORT).show()
                    finish() // Close activity after successful update
                } else {
                    // This case might occur if updatePassword returned 0 but didn't throw an exception,
                    // e.g., if the password with that ID wasn't found (though controller should throw PasswordNotFoundException for this now).
                    // Or if no actual changes were made to the data.
                    Toast.makeText(this, getString(R.string.something_went_wrong_msg) + ": No changes applied or password not found.", Toast.LENGTH_SHORT).show()
                    finish() // Still finish even if no rows affected
                }
            } catch (e: InvalidInputException) {
                Toast.makeText(this, getString(R.string.warn_fill_form), Toast.LENGTH_SHORT).show()
            } catch (e: PasswordNotFoundException) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                e.printStackTrace()
                finish() // If password not found during update, close
            } catch (e: DatabaseOperationException) {
                Toast.makeText(this, "${getString(R.string.fail_msg)}: ${e.message}", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            } catch (e: Exception) {
                Toast.makeText(this, "${getString(R.string.fail_msg)}: ${e.message}", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }
        }
    }
}
