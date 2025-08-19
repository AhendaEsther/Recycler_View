package com.advertise.recyclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

class PostRvAdapter(private var postsList: List<Post>) :
    RecyclerView.Adapter<PostRvAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_list_item, parent, false) // Make sure R.layout.item_post exists
        return PostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentPost = postsList[position]
        holder.tvTitle.text = currentPost.title
        holder.tvBody.text = currentPost.body
        // You could also display userId or id if you have TextViews for them in item_post.xml
        // holder.tvUserId.text = currentPost.userId.toString()
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    fun updatePosts(newPosts: List<Post>) {
        postsList = newPosts
        notifyDataSetChanged() // For simplicity. Consider DiffUtil for better performance.
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Make sure these IDs match the TextViews in your item_post.xml
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvBody: TextView = itemView.findViewById(R.id.tvBody)
    }
}