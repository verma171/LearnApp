package com.learn.data_movie.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.learn.data_movie.entity.MovieEntity
import com.learn.core.model.Movies

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getMovies():PagingSource<Int,MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<MovieEntity>)

    @Query("DELETE FROM movie")
    suspend fun deleteMovies()
}

