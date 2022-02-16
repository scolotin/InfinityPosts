package com.scolotin.infinityposts.repository.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.scolotin.infinityposts.model.PostsKeys
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface PostsKeysDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertKeys(keys: PostsKeys): Completable

    @Query("SELECT * FROM PostsKeys")
    fun fetchKeys(): Single<PostsKeys>
}
