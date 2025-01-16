package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class ThirdScreen : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val userList = mutableListOf<User>()
    private val firebaseHelper = FirebaseHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_screen)

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(userList) { user ->
            Toast.makeText(this, "Selected user: ${user.firstName} ${user.lastName}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = userAdapter

        swipeRefreshLayout.setOnRefreshListener {
            fetchUsers()
        }

        fetchUsers()
    }

    private fun fetchUsers() {
        swipeRefreshLayout.isRefreshing = true
        firebaseHelper.getAllUsers { users ->
            userList.clear()
            userList.addAll(users)
            userAdapter.notifyDataSetChanged()
            swipeRefreshLayout.isRefreshing = false
        }
    }
}