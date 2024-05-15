package com.learn.data_movie.di

import com.learn.data_movie.MovieRepoImpl
import com.learn.data_movie.MovieRepository
import com.learn.data_movie.MovieService
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    companion object {
        private const val BASE_URL = "https://api.themoviedb.org"
    }

    @Provides
    fun getMoviesService(retrofit: Retrofit.Builder,moshi: Moshi):MovieService {
        return retrofit.addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL).build().create(MovieService::class.java)
    }

    @Provides
    fun getMovieRepo(movieService: MovieService) : MovieRepository {
        return  MovieRepoImpl(movieService)
    }
}