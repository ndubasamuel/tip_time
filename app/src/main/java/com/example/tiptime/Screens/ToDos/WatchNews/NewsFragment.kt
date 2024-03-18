package com.example.tiptime.Screens.ToDos.WatchNews

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tiptime.Adapters.NewsListAdapter
import com.example.tiptime.AppController
import com.example.tiptime.Model.Article
import com.example.tiptime.Utils.Resource
import com.example.tiptime.ViewModel.NewsViewModel
import com.example.tiptime.ViewModel.NewsViewModelFactory
import com.example.tiptime.databinding.FragmentNewsBinding
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class NewsFragment () : Fragment() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var adapter: NewsListAdapter
    private lateinit var binding: FragmentNewsBinding

    @Inject
    lateinit var newsViewModelFactory: NewsViewModelFactory

    val TAG = "News Fragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as AppController).mApiComponent.inject(this)

        setupRecyclerView()
        viewModel = ViewModelProvider(this, newsViewModelFactory).get(NewsViewModel::class.java)

        viewModel.getLiveNews()


        viewModel.liveNews.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success<*> -> {
                    hideProgressBar()
                    val articles = resource.data
                    adapter.differ.submitList(articles as MutableList<Article>?)
                    Log.d("Fragment", "We got news")

                }
                is Resource.Error<*> -> {
                    hideProgressBar()
                    resource.message?.let { message ->
                        Log.e(TAG, "An error occurred: $message")

                        showSnackbar("Could not retrieve recent articles")
                    }
                }

                is Resource.Loading<*> -> {
                    showProgressBar()
                }

                else -> {
                    showSnackbar("Unexpected error: News Response is null")
                }
            }
        }
    }


    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT). show()
    }

    private fun hideProgressBar() {
       val progressBar = binding.newsProgressBar
        progressBar.visibility = View.INVISIBLE
    }
    private fun showProgressBar() {
        val progressBar = binding.newsProgressBar
        progressBar.visibility = View.VISIBLE
    }
    private fun setupRecyclerView() {
        adapter = NewsListAdapter()
        val recyclerView = binding.recyclerMain
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
                )
            )
        }
}






