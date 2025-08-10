package com.jeeldobariya.passcodes.ui.activities

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.jeeldobariya.passcodes.R
import com.jeeldobariya.passcodes.database.Password
import com.jeeldobariya.passcodes.databinding.ActivityUpdatePasswordBinding
import com.jeeldobariya.passcodes.utils.Controller
import com.jeeldobariya.passcodes.utils.DatabaseOperationException
import com.jeeldobariya.passcodes.utils.InvalidInputException
import com.jeeldobariya.passcodes.utils.PasswordNotFoundException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
 * Activity expects id as intent parameters.
 */
class UpdatePasswordActivity : AppCompatActivity() {

    private var passwordEntityId: Int = 0
    private lateinit var controller: Controller
    private lateinit var binding: ActivityUpdatePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdatePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        passwordEntityId = intent.getIntExtra("id", -1) // -1 is an invalid id.

        if (passwordEntityId == -1) { // invalid entity
            Toast.makeText(this, getString(R.string.toast_error_invalid_password_id), Toast.LENGTH_SHORT).show()
            finish()
            return // Exit onCreate if ID is invalid
        }

        controller = Controller(this)

        // Fill data into views
        fillDataInViews() // Call this to populate initial data

        // Add event onclick listener
        addOnClickListenerOnButton()

        // Make window fullscreen
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    @SuppressLint("SetTextI18n")
    private fun fillDataInViews() {
        lifecycleScope.launch {
            try {
                val passwordEntity: Password = controller.getPasswordById(passwordEntityId)
                withContext(Dispatchers.Main) {
                    binding.tvId.text = "${getString(R.string.prefix_id)}  $passwordEntityId"
                    binding.inputDomain.setText(passwordEntity.domain)
                    binding.inputUsername.setText(passwordEntity.username)
                    binding.inputPassword.setText(passwordEntity.password)
                    binding.inputNotes.setText(passwordEntity.notes)
                }
            } catch (e: PasswordNotFoundException) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@UpdatePasswordActivity, e.message, Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                    finish()
                }
            } catch (e: DatabaseOperationException) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@UpdatePasswordActivity, "${getString(R.string.toast_something_went_wrong)}: ${e.message}", Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                    finish()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@UpdatePasswordActivity, "${getString(R.string.toast_something_went_wrong)}: ${e.message}", Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                    finish()
                }
            }
        }
    }

    // Added all the onclick event listeners
    private fun addOnClickListenerOnButton() {
        binding.updatePasswordBtn.setOnClickListener {
            val newDomain = binding.inputDomain.text.toString()
            val newUsername = binding.inputUsername.text.toString()
            val newPassword = binding.inputPassword.text.toString()
            val newNotes = binding.inputNotes.text.toString()

            val confirmDialog = AlertDialog.Builder(this@UpdatePasswordActivity)
                .setTitle(R.string.dialog_update_password_title)
                .setMessage(R.string.dialog_irreversible_desc)
                .setPositiveButton(R.string.dialog_confirm_button) { _, _ ->
                    performUpdatePasswordAction(newDomain, newUsername, newPassword, newNotes);
                }
                .setNegativeButton(R.string.dialog_cancel_button) { _, _ ->
                    Toast.makeText(this, getString(R.string.toast_action_discarded), Toast.LENGTH_SHORT).show();
                }
                .create();
            confirmDialog.show();
        }
    }

    fun performUpdatePasswordAction(newDomain: String, newUsername: String, newPassword: String, newNotes: String) {
        lifecycleScope.launch {
            try {
                val rowsAffected = controller.updatePassword(passwordEntityId, newDomain, newUsername, newPassword, newNotes)
                withContext(Dispatchers.Main) {
                    if (rowsAffected > 0) {
                        Toast.makeText(this@UpdatePasswordActivity, getString(R.string.toast_update_success), Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@UpdatePasswordActivity, getString(R.string.toast_something_went_wrong) + ": No changes applied or password not found.", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            } catch (e: InvalidInputException) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@UpdatePasswordActivity, getString(R.string.toast_warn_fill_form), Toast.LENGTH_SHORT).show()
                }
            } catch (e: PasswordNotFoundException) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@UpdatePasswordActivity, e.message, Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                    finish()
                }
            } catch (e: DatabaseOperationException) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@UpdatePasswordActivity, "${getString(R.string.toast_fail_msg)}: ${e.message}", Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@UpdatePasswordActivity, "${getString(R.string.toast_fail_msg)}: ${e.message}", Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                }
            }
        }
    }
}
