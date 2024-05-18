package com.learn.feature_movie

import android.view.View
import android.widget.TextView
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.learn.feature_movie.databinding.MovieFooterItemBinding

class LoadingViewHolder(val view: MovieFooterItemBinding,onRetry:()->Unit) : RecyclerView.ViewHolder(view.root) {
    fun bind(loadState: LoadState) {
        when (loadState) {
            is LoadState.NotLoading -> {
                view.apply {
                    progressCircular.visibility = View.GONE
                    retryButton.visibility = View.GONE
                }
            }

            is LoadState.Loading -> {
                view.apply {
                    progressCircular.visibility = View.VISIBLE
                    retryButton.visibility = View.GONE
                }
            }

            is LoadState.Error -> {
                view.apply {
                    progressCircular.visibility = View.GONE
                    retryButton.visibility= View.VISIBLE
                }
            }
        }
    }

    init {
        view.retryButton.setOnClickListener{
            onRetry.invoke()
        }
    }
}
