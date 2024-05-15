package com.learn.data_movie

import androidx.paging.PagingData
import com.learn.core.model.Movies
import com.learn.core.model.Results
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
 suspend fun getMovies(pageNo:Int):Movies
 fun getMoviesPage(): Flow<PagingData<Results>>
}