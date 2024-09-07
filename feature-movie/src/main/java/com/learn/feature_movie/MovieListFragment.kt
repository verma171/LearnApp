package com.learn.feature_movie

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i(TAG, "onAttach: ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState: ")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.i(TAG, "onViewStateRestored: ")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: ")
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView: ")
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
        Log.i(TAG, "onViewCreated: ")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i(TAG, "onActivityCreated: ")
    }


    override fun onStart() {
        Log.i(TAG, "onStart: ")
        super.onStart()
    }


    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: ")
    }
    
    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(TAG, "onDestroyView: ")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i(TAG, "onDetach: ")
    }
    



    private fun initRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(this.requireContext())
        binding?.recyclerView?.setHasFixedSize(true)
        adapter = MovieDataAdapter(){
            shareTextContent("Test","Test")
        }
        recyclerView?.adapter = adapter
    }

    fun shareTextContent(text: String, subject: String? = null) {
        // Create an intent with ACTION_SEND action
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
            subject?.let {
                putExtra(Intent.EXTRA_SUBJECT, it)
            }
        }

        // Create a chooser intent
        val chooserIntent = Intent.createChooser(sendIntent, "Share via")

        // Start the chooser activity
        startActivity(chooserIntent)
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this.requireContext())
        builder.setTitle("Alert Dialog Title")
        builder.setMessage("This is an alert dialog message.")
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
            Log.i(TAG, "AlertDialog dismissed")
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
        Log.i(TAG, "AlertDialog shown")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: ")
    }
}