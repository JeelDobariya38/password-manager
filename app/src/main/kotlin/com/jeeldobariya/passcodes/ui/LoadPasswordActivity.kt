package com.jeeldobariya.passcodes.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.jeeldobariya.passcodes.R
import com.jeeldobariya.passcodes.database.Password // Use your Room entity directly
import com.jeeldobariya.passcodes.databinding.ActivityLoadPasswordBinding
import com.jeeldobariya.passcodes.ui.adapter.PasswordAdapter
import com.jeeldobariya.passcodes.utils.Controller
import com.jeeldobariya.passcodes.utils.DatabaseOperationException

class LoadPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoadPasswordBinding
    private lateinit var passwordAdapter: PasswordAdapter
    private lateinit var controller: Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        controller = Controller(this) // Initialize the controller here

        // Add event onclick listener
        addOnClickListenerOnButton()

        // Make window fullscreen
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    private fun fillPasswordList() {
        try {
            // This is a synchronous call and will block the UI thread.
            // For a real app, use suspend functions and coroutines/Flow here.
            val passwordList: List<Password> = controller.getAllPasswords()

            // Initialize or update the adapter
            if (!this::passwordAdapter.isInitialized) { // Check if adapter is already initialized
                passwordAdapter = PasswordAdapter(this, passwordList)
                binding.passwordList.adapter = passwordAdapter
            } else {
                passwordAdapter.updateData(passwordList) // Update existing adapter
            }
        } catch (e: DatabaseOperationException) {
            Toast.makeText(this, "${getString(R.string.something_went_wrong_msg)}: ${e.message}", Toast.LENGTH_LONG).show()
            e.printStackTrace()
            // Optionally, set an empty adapter or show a message if loading fails
            if (!this::passwordAdapter.isInitialized) {
                passwordAdapter = PasswordAdapter(this, emptyList())
                binding.passwordList.adapter = passwordAdapter
            }
        } catch (e: Exception) {
            Toast.makeText(this, "${getString(R.string.something_went_wrong_msg)}: ${e.message}", Toast.LENGTH_LONG).show()
            e.printStackTrace()
            if (!this::passwordAdapter.isInitialized) {
                passwordAdapter = PasswordAdapter(this, emptyList())
                binding.passwordList.adapter = passwordAdapter
            }
        }
    }

    // Added all the onclick event listeners
    private fun addOnClickListenerOnButton() {
        binding.passwordList.setOnItemClickListener { _, _, position, _ ->
            // getItem returns Any, so we cast it to Password
            val selectedPassword = passwordAdapter.getItem(position) as Password

            // Do something with the selectedPassword
            val intent = Intent(this, ViewPasswordActivity::class.java)
            intent.putExtra("id", selectedPassword.id) // Pass the ID from the Password entity
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        fillPasswordList() // Refresh the list whenever the activity resumes
    }
}