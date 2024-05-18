package com.learn.feature_movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.learn.feature_movie.databinding.MovieFooterItemBinding

class FooterAdapter(val onRetry:()->Unit): LoadStateAdapter<LoadingViewHolder>() {
    override fun onBindViewHolder(holder: LoadingViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadingViewHolder {
        val view =  MovieFooterItemBinding.bind( LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_footer_item, parent, false))
        return LoadingViewHolder(view){
            onRetry.invoke()
        }
    }
}