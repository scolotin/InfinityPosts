package com.scolotin.infinityposts.repository.api

import com.scolotin.infinityposts.model.Post

fun PostDTO.mapToPostList() = this.data.children.map { children ->
    Post(children.data.title, children.data.ups, children.data.num_comments)
}
