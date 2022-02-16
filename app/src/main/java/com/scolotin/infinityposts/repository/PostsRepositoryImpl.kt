package com.scolotin.infinityposts.repository


import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.scolotin.infinityposts.repository.db.PostsDB
import com.scolotin.infinityposts.model.Post
import io.reactivex.rxjava3.core.Flowable

@ExperimentalPagingApi
class PostsRepositoryImpl(
    private val postsRemoteMediator: PostsRemoteMediator,
    private val postsDB: PostsDB
) : PostsRepository {

    override fun getPosts(): Flowable<PagingData<Post>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                prefetchDistance = 3
            ),
            remoteMediator = postsRemoteMediator,
            pagingSourceFactory = { postsDB.getPostsDAO().fetchPosts() }
        ).flowable
    }

}
