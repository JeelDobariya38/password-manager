package com.jeeldobariya.passcodes.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.jeeldobariya.passcodes.R
import com.jeeldobariya.passcodes.database.Password
import com.jeeldobariya.passcodes.databinding.ActivityViewPasswordBinding
import com.jeeldobariya.passcodes.utils.Controller
import com.jeeldobariya.passcodes.utils.DatabaseOperationException
import com.jeeldobariya.passcodes.utils.PasswordNotFoundException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
 * Activity expects id as intent parameters.
 */
class ViewPasswordActivity : AppCompatActivity() {

    private var passwordEntityId: Int = 0
    private lateinit var binding: ActivityViewPasswordBinding
    private lateinit var controller: Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        passwordEntityId = intent.getIntExtra("id", -1) // -1 is an invalid id.

        if (passwordEntityId == -1) { // invalid entity
            Toast.makeText(this, getString(R.string.error_invalid_password_id), Toast.LENGTH_SHORT).show()
            finish()
            return // Exit onCreate if ID is invalid
        }

        controller = Controller(this)

        // Add event onclick listener
        addOnClickListenerOnButton()

        // Make window fullscreen
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    private fun fillDataInTextview() {
        lifecycleScope.launch {
            try {
                val passwordEntity: Password = controller.getPasswordById(passwordEntityId)
                withContext(Dispatchers.Main) {
                    binding.tvDomain.text = "${getString(R.string.domain_prefix)}  ${passwordEntity.domain}"
                    binding.tvUsername.text = "${getString(R.string.username_prefix)}  ${passwordEntity.username}"
                    binding.tvPassword.text = "${getString(R.string.password_prefix)}  ${passwordEntity.password}"
                    binding.tvNotes.text = "${getString(R.string.notes_prefix)}  ${passwordEntity.notes}"
                    binding.tvCreatedAt.text = "${getString(R.string.createdat_prefix)}  ${passwordEntity.createdAt}"
                    binding.tvUpdatedAt.text = "${getString(R.string.updatedat_prefix)}  ${passwordEntity.updatedAt}"
                }
            } catch (e: PasswordNotFoundException) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ViewPasswordActivity, e.message, Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                    finish()
                }
            } catch (e: DatabaseOperationException) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ViewPasswordActivity, "${getString(R.string.something_went_wrong_msg)}: ${e.message}", Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                    finish()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ViewPasswordActivity, "${getString(R.string.something_went_wrong_msg)}: ${e.message}", Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                    finish()
                }
            }
        }
    }

    // Added all the onclick event listeners
    private fun addOnClickListenerOnButton() {
        binding.updatePasswordBtn.setOnClickListener {
            val viewPasswordIntent = Intent(this, UpdatePasswordActivity::class.java)
            viewPasswordIntent.putExtra("id", passwordEntityId)
            startActivity(viewPasswordIntent)
        }

        binding.deletePasswordBtn.setOnClickListener {
            lifecycleScope.launch {
                try {
                    val rowsDeleted = controller.deletePassword(passwordEntityId)
                    withContext(Dispatchers.Main) {
                        if (rowsDeleted > 0) {
                            Toast.makeText(this@ViewPasswordActivity, getString(R.string.delete_success_msg), Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(this@ViewPasswordActivity, getString(R.string.something_went_wrong_msg) + ": Password not found for deletion or no rows affected.", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }
                } catch (e: DatabaseOperationException) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@ViewPasswordActivity, "${getString(R.string.something_went_wrong_msg)}: ${e.message}", Toast.LENGTH_LONG).show()
                        e.printStackTrace()
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@ViewPasswordActivity, "${getString(R.string.something_went_wrong_msg)}: ${e.message}", Toast.LENGTH_LONG).show()
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        fillDataInTextview()
    }
}
