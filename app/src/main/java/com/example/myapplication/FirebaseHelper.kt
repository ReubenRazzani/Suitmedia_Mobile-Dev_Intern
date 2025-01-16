package com.example.myapplication

class FirebaseHelper {

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val userRef: DatabaseReference = database.reference.child("users")

    // Mengambil seluruh data pengguna
    fun getAllUsers(callback: (List<User>) -> Unit) {
        userRef.get()
            .addOnSuccessListener { snapshot ->
                val userList = mutableListOf<User>()
                for (dataSnapshot in snapshot.children) {
                    val user = dataSnapshot.getValue(User::class.java)
                    user?.let { userList.add(it) }
                }
                callback(userList)
            }
            .addOnFailureListener { exception ->
                callback(emptyList()) // Jika gagal, kirimkan daftar kosong
            }
    }
}