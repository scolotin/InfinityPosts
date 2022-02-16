package com.scolotin.infinityposts.repository.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.scolotin.infinityposts.model.Post
import io.reactivex.rxjava3.core.Completable

@Dao
interface PostsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<Post>): Completable

    @Query("SELECT * from Post")
    fun fetchPosts(): PagingSource<Int, Post>
}
