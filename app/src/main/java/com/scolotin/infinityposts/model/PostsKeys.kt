package com.scolotin.infinityposts.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class PostsKeys(
    @PrimaryKey
    val id: Long,
    val nextKey: String? = null,
    val prevKey: String? = null
) : Parcelable
