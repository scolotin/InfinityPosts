package com.scolotin.infinityposts

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import com.scolotin.infinityposts.di.databaseModule
import com.scolotin.infinityposts.di.mainModule
import com.scolotin.infinityposts.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@ExperimentalPagingApi
class InfinityPosts : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(listOf(mainModule, networkModule, databaseModule))
        }
    }

}
