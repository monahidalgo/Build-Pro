package com.example.buildpro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.buildpro.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get references to UI elements
        val usernameEditText = findViewById<EditText>(R.id.username_edit_text)
        val passwordEditText = findViewById<EditText>(R.id.password_edit_text)
        val loginButton = findViewById<Button>(R.id.login_button)
        val createAccountTextView = findViewById<TextView>(R.id.create_account_text_view)
        val forgotPasswordTextView = findViewById<TextView>(R.id.forgot_password_text_view)

        // Set up click listener for login button
        loginButton.setOnClickListener {
            // Get user input
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Validate user input
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter a Username and Password", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            // Perform login
            // (Replace this with your own login logic)
            if (username == "admin" && password == "password") {
                val intent = Intent(this, HomeScreenActivity::class.java)
                startActivity(intent) // Start HomeScreenActivity on successful login
            } else {
                Toast.makeText(this, "Invalid Yername or password", Toast.LENGTH_LONG).show()
            }
        }

        // Set up click listener for "Create Account"
        createAccountTextView.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }

        // Set up click listener for "Forgot Password"
        forgotPasswordTextView.setOnClickListener {
            // TODO: Implement Forgot Password functionality (e.g., navigate to a ForgotPasswordActivity)
            Toast.makeText(this, "Forgot Password Emailed!", Toast.LENGTH_SHORT).show()
        }
    }
}
