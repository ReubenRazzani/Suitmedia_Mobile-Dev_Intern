import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.User
import com.example.myapplication.UserAdapter

class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val userList = mutableListOf<User>()
    private val firebaseHelper = FirebaseHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_screen)

        recyclerView = findViewById(R.id.recyclerView)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)

        recyclerView.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(userList) { user ->
            // Menangani klik item
            Toast.makeText(this, "Selected user: ${user.firstName} ${user.lastName}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = userAdapter

        // Menambahkan fitur Pull-to-Refresh
        swipeRefreshLayout.setOnRefreshListener {
            fetchUsers()
        }

        // Ambil data pengguna pertama kali
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
