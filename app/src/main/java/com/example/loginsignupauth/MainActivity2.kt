package com.example.loginsignupauth

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.fragment.app.Fragment


class MainActivity2 : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // Initialize DrawerLayout and NavigationView
        drawerLayout = findViewById(R.id.drawerlayout)
        navigationView = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.toolbar)

        // Set the Toolbar as the ActionBar
        setSupportActionBar(toolbar)

        // Set the Navigation Item Selected Listener
        navigationView.setNavigationItemSelectedListener(this)

        // Setup the DrawerToggle
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.open_nav, R.string.close_nav
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Load the default fragment (HomeFragment) when the activity is created
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment2()).commit()
            navigationView.setCheckedItem(R.id.nav_home2)
        }
    }

    // Handle the navigation item selections
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment: Fragment = when (item.itemId) {
            R.id.nav_home2 -> HomeFragment2()
            R.id.nav_person -> PersonFragment()
            R.id.nav_settings -> SettingsFragment()
            R.id.nav_share -> ShareFragment()
            R.id.nav_about -> AboutFragment()
            R.id.nav_logout -> {


                // Handle logout logic here (like starting the LoginActivity)
                val intent = Intent(this, CoordinatorActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()  // Finish MainActivity to avoid going back to it
                return true
            }
            else -> HomeFragment2()
        }

        // Replace the fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

        // Close the drawer
        drawerLayout.closeDrawers()
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
