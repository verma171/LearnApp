package com.learn.feature_movie

import com.learn.core.model.Movies
import com.learn.data_movie.MovieRepository
import com.learn.ui.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.learn.core.LearnLogger
import com.learn.core.model.Results
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    val list: Flow<PagingData<Results>> = movieRepository.getMoviesPage().cachedIn(viewModelScope)
    /*var movieSharedFlow:Flow<PagingData<Results>>? = null

    init {
            movieSharedFlow =
                movieRepository.getMoviesPage(1,viewModelScope)
    }*/

    /*fun loadMovies():Flow<PagingData<Results>>{
        return movieRepository.getMoviesPage(1,viewModelScope)
    }*/

}