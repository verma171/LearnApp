package com.learn.data_movie.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity (
    @PrimaryKey(autoGenerate = false)
    val id:String,
    val title:String
    )

@Entity(tableName = "keys")
data class RemoveKeys (
    val id:String,
    val prevKey:Int?,
    val nextKey:Int?
)