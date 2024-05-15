package com.learn.feature_movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learn.feature_movie.databinding.FragmentMovielistBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MovieListFragment : Fragment() {
    val viewmodel:MovieViewModel by viewModels()
    var binding:FragmentMovielistBinding? = null
    var adapter:MovieDataAdapter? = null
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
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(view)
        lifecycleScope.launch {
                viewmodel.list.collect{
                    adapter?.submitData(lifecycle,it)
                }
        }
    }

    private fun initRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(this.requireContext())
        binding?.recyclerView?.setHasFixedSize(true)
        adapter = MovieDataAdapter()
        recyclerView?.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}