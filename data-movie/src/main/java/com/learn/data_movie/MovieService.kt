package com.learn.data_movie

import com.learn.core.model.Movies
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieService  {
      @GET("3/discover/movie")
      suspend fun getMoviesList(@Query("page") page: Int) : Movies
}

