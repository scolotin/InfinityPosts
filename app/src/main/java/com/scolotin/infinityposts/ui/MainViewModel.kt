package com.scolotin.infinityposts.ui

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.scolotin.infinityposts.model.Post
import com.scolotin.infinityposts.repository.PostsRepository
import io.reactivex.rxjava3.core.Flowable

class MainViewModel(
    private val repository: PostsRepository
) : ViewModel() {

    fun getPosts(): Flowable<PagingData<Post>> {
        return repository.getPosts()
    }

}
