package com.scolotin.infinityposts.repository.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApi {
    @GET("/hot.json")
    fun getPosts(
        @Query("after") after: String? = null,
        @Query("before") before: String? = null
    ): Single<PostDTO>
}
