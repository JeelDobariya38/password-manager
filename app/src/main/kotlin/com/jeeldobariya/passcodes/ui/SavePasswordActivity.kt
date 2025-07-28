package com.jeeldobariya.passcodes.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.jeeldobariya.passcodes.R
import com.jeeldobariya.passcodes.databinding.ActivitySavePasswordBinding
import com.jeeldobariya.passcodes.utils.Controller
import com.jeeldobariya.passcodes.utils.DatabaseOperationException
import com.jeeldobariya.passcodes.utils.InvalidInputException

class SavePasswordActivity : AppCompatActivity() {

    private lateinit var controller: Controller // Use lateinit since it's initialized in onCreate
    private lateinit var binding: ActivitySavePasswordBinding // Use lateinit for binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySavePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        controller = Controller(this) // Initialize controller

        // Add event onclick listener
        addOnClickListenerOnButton() // Pass binding via class member

        // Make window fullscreen
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    // Added all the onclick event listeners
    private fun addOnClickListenerOnButton() {
        binding.savePasswordBtn.setOnClickListener {
            val domain = binding.inputDomain.text.toString()
            val username = binding.inputUsername.text.toString()
            val password = binding.inputPassword.text.toString()
            val notes = binding.inputNotes.text.toString()

            try {
                // Call the controller method which now throws exceptions
                val rowId = controller.savePasswordEntity(domain, username, password, notes)
                Toast.makeText(this, "${getString(R.string.sucess_clause)} $rowId", Toast.LENGTH_SHORT).show()
                finish() // Optionally close the activity after successful save
            } catch (e: InvalidInputException) {
                // Handle the case where input parameters are empty/blank
                Toast.makeText(this, getString(R.string.warn_fill_form), Toast.LENGTH_SHORT).show()
            } catch (e: DatabaseOperationException) {
                // Handle general database operation errors
                Toast.makeText(this, "${getString(R.string.fail_msg)}: ${e.message}", Toast.LENGTH_LONG).show()
                e.printStackTrace() // Log the stack trace for debugging
            } catch (e: Exception) {
                // Catch any other unexpected exceptions
                Toast.makeText(this, "${getString(R.string.fail_msg)}: ${e.message}", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }
        }
    }
}
