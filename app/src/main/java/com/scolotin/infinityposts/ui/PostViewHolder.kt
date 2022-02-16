package com.scolotin.infinityposts.ui

import androidx.recyclerview.widget.RecyclerView
import com.scolotin.infinityposts.databinding.ItemPostBinding
import com.scolotin.infinityposts.model.Post

class PostViewHolder(private val vb: ItemPostBinding) : RecyclerView.ViewHolder(vb.root) {

    fun bind(post: Post) {
        with(vb) {
            postText.text = post.text
            postRate.text = post.rate.toString()
            postComment.text = post.commentCount.toString()
        }
    }

}