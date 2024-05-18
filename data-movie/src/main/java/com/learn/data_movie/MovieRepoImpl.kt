package com.learn.data_movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import com.learn.core.model.Movies
import com.learn.core.model.Results
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepoImpl @Inject constructor(val movieService: MovieService) : MovieRepository {
    override suspend fun getMovies(pageNo: Int): Movies {
        return movieService.getMoviesList(pageNo)
    }

    override fun getMoviesPage(): Flow<PagingData<Results>> {
        return Pager(
            config = PagingConfig(20),
            pagingSourceFactory = {
                object : PagingSource<Int, Results>() {
                    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
                        return state.anchorPosition?.let { anchorPosition ->
                            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
                        }
                    }

                    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
                        val position = params.key ?: 1
                        return try {
                            if (position > 1) {
                                delay(2000)
                            }
                            withContext(Dispatchers.IO) {
                                val movies = movieService.getMoviesList(position)
                                LoadResult.Page(
                                    prevKey = if (position == 1) null else position - 1,
                                    nextKey = position + 1,
                                    data = movies.results
                                )
                            }
                        } catch (e: Exception) {
                            LoadResult.Error(e)
                        }
                    }
                }
            }
        ).flow
    }
}