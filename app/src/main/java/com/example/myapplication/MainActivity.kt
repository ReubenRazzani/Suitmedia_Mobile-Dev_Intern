package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnFirstScreen = findViewById<Button>(R.id.btn_first_screen)
        btnFirstScreen.setOnClickListener {
            val intent = Intent(this, FirstScreen::class.java)
            startActivity(intent)
        }

        val btnSecondScreen = findViewById<Button>(R.id.btn_second_screen)
        btnSecondScreen.setOnClickListener {
            val intent = Intent(this, SecondScreen::class.java)
            startActivity(intent)
        }

        val btnThirdScreen = findViewById<Button>(R.id.btn_third_screen)
        btnThirdScreen.setOnClickListener {
            val intent = Intent(this, ThirdScreen::class.java)
            startActivity(intent)
        }
    }
}