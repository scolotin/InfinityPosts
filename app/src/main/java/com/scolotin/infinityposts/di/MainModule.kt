package com.scolotin.infinityposts.di

import androidx.paging.ExperimentalPagingApi
import com.scolotin.infinityposts.repository.PostsRemoteMediator
import com.scolotin.infinityposts.repository.PostsRepository
import com.scolotin.infinityposts.repository.PostsRepositoryImpl
import com.scolotin.infinityposts.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalPagingApi
val mainModule = module {
    factory { PostsRemoteMediator(redditApi = get(), postsDB = get()) }
    factory<PostsRepository> { PostsRepositoryImpl(postsRemoteMediator = get(), postsDB = get()) }
    viewModel { MainViewModel(repository = get()) }
}
