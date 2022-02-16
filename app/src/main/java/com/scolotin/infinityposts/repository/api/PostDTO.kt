package com.scolotin.infinityposts.repository.api

data class ChildrenData(
    val title: String,
    val num_comments: Long,
    val ups: Long
)

data class Children(
    val data: ChildrenData
)

data class PostData(
    val after: String?,
    val before: String?,
    val children: ArrayList<Children>
)

data class PostDTO(
    val data: PostData
)
