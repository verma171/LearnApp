package com.learn.feature_movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learn.feature_movie.databinding.FragmentMovielistBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MovieListFragment : Fragment() {
    val viewmodel:MovieViewModel by viewModels()
    var binding:FragmentMovielistBinding? = null
    private val TAG = "MovieListFragment"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movielist,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView(view)


    }

    private fun initRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(this.requireContext())
        binding?.recyclerView?.setHasFixedSize(true)
        val movieDataAdapter = MovieDataAdapter()
        recyclerView?.adapter =movieDataAdapter.withLoadStateFooter(FooterAdapter{
            movieDataAdapter.retry()
        })

        lifecycleScope.launch {
            viewmodel.list.collect{
                movieDataAdapter.submitData(lifecycle,it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}