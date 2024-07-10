package com.example.buildpro

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class HomeScreenActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var projectRecyclerView: RecyclerView
    private val projects = mutableListOf<String>()
    private lateinit var projectAdapter: ProjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        drawerLayout = findViewById(R.id.drawer_layout)
        projectRecyclerView = findViewById(R.id.projectRecyclerView)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Set profile information in the header (fetch from user data)
        val headerView = navigationView.getHeaderView(0)
        val profileNameTextView = headerView.findViewById<TextView>(R.id.profileNameTextView)
        val profilePhoneTextView = headerView.findViewById<TextView>(R.id.profilePhoneTextView)
        val profileEmailTextView = headerView.findViewById<TextView>(R.id.profileEmailTextView)

        profileNameTextView.text = "Mona Hidalgo" // Replace with actual user name
        profilePhoneTextView.text = "Phone: 323-400-0421" // Replace with actual phone
        profileEmailTextView.text = "Email: mona@bittleco.com" // Replace with actual email

        // Set up RecyclerView
        projectAdapter = ProjectAdapter(projects) { projectName ->
            Toast.makeText(this, "Clicked on project: $projectName", Toast.LENGTH_SHORT).show()
        }
        projectRecyclerView.adapter = projectAdapter
        projectRecyclerView.layoutManager = LinearLayoutManager(this)

        // Floating Action Button for adding new projects
        val fab: FloatingActionButton = findViewById(R.id.floatingActionButton)
        fab.setOnClickListener {
            showCreateProjectDialog()
        }
    }

    private fun showCreateProjectDialog() {
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.dialog_create_project, null)
        val projectNameEditText = view.findViewById<EditText>(R.id.projectNameEditText)

        builder.setView(view)
            .setPositiveButton("Create") { dialog, _ ->
                val projectName = projectNameEditText.text.toString()
                if (projectName.isNotEmpty()) {
                    projects.add(projectName)
                    projectAdapter.notifyItemInserted(projects.size - 1)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_project_name -> {
                // Navigate to Project Name screen
            }
            R.id.nav_change_password -> {
                // Navigate to Change Password screen
            }
            R.id.nav_support -> {
                // Navigate to Support screen
            }
            R.id.nav_logout -> {
                // Handle logout
                clearUserSession()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun clearUserSession() {
        // Implement your logic to clear user session
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
