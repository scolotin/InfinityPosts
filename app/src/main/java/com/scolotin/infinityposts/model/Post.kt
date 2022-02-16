package com.scolotin.infinityposts.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Post(
    @PrimaryKey
    val text: String = "",
    val rate: Long = 0,
    val commentCount: Long = 0
) : Parcelable
