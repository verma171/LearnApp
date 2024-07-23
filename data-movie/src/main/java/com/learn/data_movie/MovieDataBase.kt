package com.learn.data_movie

import androidx.room.Database
import androidx.room.RoomDatabase
import com.learn.data_movie.dao.MovieDao
import com.learn.data_movie.dao.RemoteKeysDao
import com.learn.data_movie.entity.MovieEntity
import com.learn.data_movie.entity.QuoteRemoteKeys
import com.learn.data_movie.entity.RemoveKeys

@Database(entities = [MovieEntity::class, QuoteRemoteKeys::class], version = 1)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}