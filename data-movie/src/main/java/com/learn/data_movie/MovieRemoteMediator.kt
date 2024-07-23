package com.learn.data_movie

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingData
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.learn.core.model.Movies
import com.learn.core.model.Results
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(val movieService: MovieService,val moveDataBase: MovieDataBase): RemoteMediator<Int,Results>(){

    val movieDao = moveDataBase.movieDao()
    val remoteKeyDao = moveDataBase.remoteKeysDao()
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Results>
    ): MediatorResult {
        /*try {
            val currentpage = 1
            val results = movieService.getMoviesList(currentpage)

            val endOfPage = false
            val prePage = if (currentpage == 1) null else

            moveDataBase.withTransaction {

            }
        } catch (e:Exception){

        }*/
      return MediatorResult.Success(true)
    }
}