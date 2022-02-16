package com.scolotin.infinityposts.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.scolotin.infinityposts.model.PostsKeys
import com.scolotin.infinityposts.model.Post

@Database(
    entities = [PostsKeys::class, Post::class],
    version = 1,
    exportSchema = false
)
abstract class PostsDB : RoomDatabase() {

    abstract fun getPostsKeyDAO(): PostsKeysDAO
    abstract fun getPostsDAO(): PostsDAO
}
