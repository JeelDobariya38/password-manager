package com.jeeldobariya.passcodes.ui.activities

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController

import com.jeeldobariya.passcodes.R
import com.jeeldobariya.passcodes.databinding.ActivityMainBinding

// import com.jeeldobariya.passcodes.utils.Permissions

class MainActivity : AppCompatActivity() {

    // private lateinit var permissionsHandle: Permissions

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavController()
        setupBottomNavigation()

        // Make window fullscreen
        WindowCompat.setDecorFitsSystemWindows(window, false)

        /* Comment the code as permission is not need to write into app data dir.
            // Check and request permission when the app is first opened
            permissionsHandle = Permissions(this)
            if (!permissionsHandle.checkPermission()) permissionsHandle.requestPermission()
        */
    }

    /* Comment the code as permission is not need to write into app data dir.
        override fun onRequestPermissionsResult(
            requestCode: Int, permissions: Array<String>, grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if (requestCode == Permissions.PERMISSION_REQUEST_CODE) {
                if (permissionsHandle.isPermissionGranted(grantResults)) {
                    // Permission has been granted
                    Toast.makeText(this, getString(R.string.permission_granted), Toast.LENGTH_LONG).show()
                } else {
                    // Permission not granted
                    Toast.makeText(this, getString(R.string.permission_denied), Toast.LENGTH_LONG).show()
                }
            }
        }
    */

    private fun setupNavController() {
        // Get NavHostFragment
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fragment_container
        ) as NavHostFragment

        // Get the NavController from NavHostFragment
        navController = navHostFragment.navController

        // Connect BottomNavigationView with NavController
        binding.bottomNavigation.setupWithNavController(navController)
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.nav_item_home -> {
                    navController.navigate(R.id.home_fragment)
                    true
                }
                R.id.nav_item_security -> {
                    navController.navigate(R.id.security_fragment)
                    true
                }
                else -> false
            }
        }
    }
}
