package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FirstScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)

        val etName = findViewById<EditText>(R.id.et_name)
        val etSentence = findViewById<EditText>(R.id.et_sentence)
        val btnCheck = findViewById<Button>(R.id.btn_check)
        val btnNext = findViewById<Button>(R.id.btn_next)

        fun isPalindrome(input: String): Boolean {
            val sanitized = input.replace(Regex("[^A-Za-z0-9]"), "").lowercase()
            return sanitized == sanitized.reversed()
        }

        btnCheck.setOnClickListener {
            val sentence = etSentence.text.toString()

            if (sentence.isEmpty()) {
                showDialog("Please enter a sentence")
            } else {
                val result = if (isPalindrome(sentence)) {
                    "Palindrome"
                } else {
                    "Not Palindrome"
                }
                showDialog(result)
            }
        }

        btnNext.setOnClickListener {
            val name = etName.text.toString()
            if (name.isEmpty()) {
                showDialog("Please enter your name")
            } else {
                val intent = Intent(this, SecondScreen::class.java)
                intent.putExtra("user_name", name)
                startActivity(intent)
            }
        }
    }

    private fun showDialog(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
}