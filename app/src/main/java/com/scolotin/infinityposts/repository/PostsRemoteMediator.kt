package com.scolotin.infinityposts.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.rxjava3.RxRemoteMediator
import com.scolotin.infinityposts.repository.db.PostsDB
import com.scolotin.infinityposts.model.PostsKeys
import com.scolotin.infinityposts.model.Post
import com.scolotin.infinityposts.repository.api.RedditApi
import com.scolotin.infinityposts.repository.api.mapToPostList
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class PostsRemoteMediator(
    private val redditApi: RedditApi,
    private val postsDB: PostsDB
) : RxRemoteMediator<Int, Post>() {

    override fun loadSingle(
        loadType: LoadType,
        state: PagingState<Int, Post>,
    ): Single<MediatorResult> = Single
        .fromCallable { loadPosts(loadType) }
        .subscribeOn(Schedulers.io())

    private fun loadPosts(loadType: LoadType): MediatorResult {
        try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val postsKeys: PostsKeys? = loadPostsKeys()
                    if (postsKeys?.nextKey == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    postsKeys.nextKey
                }
            }

            redditApi.getPosts(
                after = loadKey,
                before = null
            ).subscribeOn(Schedulers.io())
                .map {
                    savePostsKeys(it.data.after, it.data.before)

                    it.mapToPostList()
                }
                .map {
                    savePostList(it)
                }
                .subscribe()

            return MediatorResult.Success(false)

        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

    private fun loadPostsKeys(): PostsKeys? {
        var postsKeys: PostsKeys? = null
        postsDB.getPostsKeyDAO()
            .fetchKeys()
            .subscribeOn(Schedulers.io())
            .blockingSubscribe {
                postsKeys = it
            }

        return postsKeys
    }

    private fun savePostsKeys(afterKey: String?, beforeKey: String?) {
        val keys = PostsKeys(0, afterKey, beforeKey)
        postsDB.getPostsKeyDAO()
            .insertKeys(keys)
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    private fun savePostList(posts: List<Post>) {
        postsDB.getPostsDAO()
            .insertPosts(posts)
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}
