package com.example.buildpro

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import kotlinx.coroutines.launch
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.buildpro.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


// Data class for Project


// Room Database
@Database(entities = [Project::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
}



// DAO for Project
interface ProjectDao {
    @androidx.room.Query("SELECT * FROM construction_items")
    suspend fun getAllProjects(): List<Project>

    @androidx.room.Insert
    suspend fun insertProject(project: Project): Long

    @androidx.room.Delete
    suspend fun deleteProject(project: Project)
}

    abstract class HomeScreenActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    abstract val ProjectToolsActivity: Intent
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var projectRecyclerView: RecyclerView
    private val projects = mutableListOf<Project>()
    private lateinit var projectAdapter: ProjectAdapter
    private lateinit var projectDao: ProjectDao
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        // Initialize database and DAO
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "project-database"
        ).build()
        projectDao = db.projectDao()

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
        projectAdapter = ProjectAdapter(projects, { project ->
            val intent = Intent(this, ProjectToolsActivity::class.java)
            intent.putExtra("project_name", project.name)
            startActivity(intent)
        }, { project ->
            deleteProject(project)
        })
        projectRecyclerView.adapter = projectAdapter
        projectRecyclerView.layoutManager = LinearLayoutManager(this)

        // Load projects from database
        loadProjects()

        // Floating Action Button for adding new projects
        val fab: FloatingActionButton = findViewById(R.id.floatingActionButton)
        fab.setOnClickListener {
            showCreateProjectDialog()
        }

        // Set up Bottom Navigation Bar
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_projects -> {
                    // Handle navigation to Projects
                }
                R.id.nav_activity -> {
                    // Handle navigation to Activity Today
                }
                R.id.nav_photos -> {
                    // Handle navigation to Photos
                }
                R.id.nav_insights -> {
                    // Handle navigation to Insights
                }
            }
            true
        }
    }

    @SuppressLint("MissingInflatedId")
    private fun showCreateProjectDialog() {
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.dialog_create_project, null)
        val projectNameEditText = view.findViewById<EditText>(R.id.projectNameEditText)
        val jobNumberEditText = view.findViewById<EditText>(R.id.jobNumberEditText)

        builder.setView(view)
            .setPositiveButton("Create") { dialog, _ ->
                val projectName = projectNameEditText.text.toString()
                val jobNumber = jobNumberEditText.text.toString()
                if (projectName.isNotEmpty() && jobNumber.isNotEmpty()) {
                    val newProject = Project(name = projectName, jobNumber = jobNumber, imageResId = R.drawable.iconsite)
                    saveProject(newProject)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun loadProjects() {
        CoroutineScope(Dispatchers.IO).launch {
            val loadedProjects = projectDao.getAllProjects()
            projects.clear()
            projects.addAll(loadedProjects)
            runOnUiThread {
                projectAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun saveProject(project: Project) {
        CoroutineScope(Dispatchers.IO).launch {
            val id = projectDao.insertProject(project)
            project.id = id.toInt() // Assuming id is of type Long
            projects.add(project)
            runOnUiThread {
                projectAdapter.notifyItemInserted(projects.size - 1)
            }
        }
    }

    private fun deleteProject(project: Project) {
        CoroutineScope(Dispatchers.IO).launch {
            projectDao.deleteProject(project)
            val position = projects.indexOf(project)
            projects.remove(project)
            runOnUiThread {
                projectAdapter.notifyItemRemoved(position)
            }
        }
    }

    override fun onNavigationItemSelected(item: android.view.MenuItem): Boolean {
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

private fun Any.toInt(): Any {
    TODO("Not yet implemented")

}

