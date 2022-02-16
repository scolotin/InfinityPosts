package com.scolotin.infinityposts.repository

import androidx.paging.PagingData
import com.scolotin.infinityposts.model.Post
import io.reactivex.rxjava3.core.Flowable

interface PostsRepository {
    fun getPosts(): Flowable<PagingData<Post>>
}
