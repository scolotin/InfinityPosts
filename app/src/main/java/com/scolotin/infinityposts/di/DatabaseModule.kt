package com.scolotin.infinityposts.di

import android.content.Context
import androidx.room.Room
import com.scolotin.infinityposts.repository.db.PostsDB
import org.koin.dsl.module

val databaseModule = module {
    single { providePostsDB(context = get()) }
}

fun providePostsDB(context: Context): PostsDB =
    Room.databaseBuilder(context, PostsDB::class.java, "posts.db").build()
