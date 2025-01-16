package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondScreen : AppCompatActivity() {

    private lateinit var tvNameFromFirstScreen: TextView
    private lateinit var tvSelectedUser: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)

        tvNameFromFirstScreen = findViewById(R.id.tv_name_from_first_screen)
        tvSelectedUser = findViewById(R.id.tv_selected_user)
        val btnChooseUser = findViewById<Button>(R.id.btn_choose_user)
        val ivBack = findViewById<ImageView>(R.id.iv_back)

        val userName = intent.getStringExtra("user_name") ?: "No Name Provided"
        tvNameFromFirstScreen.text = "$userName"

        btnChooseUser.setOnClickListener {
            val intent = Intent(this, ThirdScreen::class.java)
            startActivityForResult(intent, 1001)
        }

        ivBack.setOnClickListener {
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1001 && resultCode == RESULT_OK) {
            val selectedUser = data?.getStringExtra("selected_user_name") ?: "No User Selected"
            tvSelectedUser.text = "$selectedUser"
        }
    }
}
