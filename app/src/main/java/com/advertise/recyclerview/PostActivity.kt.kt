package com.advertise.recyclerview


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isNotEmpty
import androidx.core.view.size
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class `PostActivity.kt` : AppCompatActivity() {

    private lateinit var rvPosts: RecyclerView
    private lateinit var postAdapter: PostRvAdapter
    private var postsList = mutableListOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_post) // Make sure R.layout.activity_post exists
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvPosts = findViewById(R.id.rvPost) // Make sure rvPost ID exists in activity_post.xml
        setupRecyclerView()
        fetchPosts()
    }

    private fun setupRecyclerView() {
        postAdapter = PostRvAdapter(postsList) // Initialize with an empty or initial list
        rvPosts.layoutManager = LinearLayoutManager(this)
        rvPosts.adapter = postAdapter
    }

    private fun fetchPosts() {
        lifecycleScope.launch {
            try {
                val fetchedPosts = RetrofitInstance.api.getPosts()
                    Log.d("PostActivity", "Fetched ${fetchedPosts.size} posts.")
                    postsList.clear()
                    postsList.addAll(fetchedPosts)
                    postAdapter.updatePosts(fetchedPosts) // Or just postAdapter.notifyDataSetChanged() if you modified postsList directly
                } else {
                    Log.d("PostActivity", "No posts fetched or API returned empty list.")
                    Toast.makeText(this@`PostActivity.kt`, "No posts found", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("PostActivity", "Error fetching posts: ${e.message}", e)
                Toast.makeText(this@`PostActivity.kt`, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}