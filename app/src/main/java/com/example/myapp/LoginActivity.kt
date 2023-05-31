package com.example.myapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()  // This hides the ActionBar
        setContentView(R.layout.activity_login)

        val myButton = findViewById<Button>(R.id.login)

        myButton.setOnClickListener {
            val intent = Intent(this, LandingScreenActivity::class.java)
            startActivity(intent)
        }
    }
}