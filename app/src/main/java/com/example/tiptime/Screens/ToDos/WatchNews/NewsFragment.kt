package com.example.tiptime.Screens.ToDos.WatchNews

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tiptime.Adapters.NewsListAdapter
import com.example.tiptime.MainActivity
import com.example.tiptime.Utils.Resource
import com.example.tiptime.Utils.Utils
import com.example.tiptime.ViewModel.NewsViewModel
import com.example.tiptime.databinding.FragmentNewsBinding
import com.google.android.material.snackbar.Snackbar


class NewsFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var adapter: NewsListAdapter
    private lateinit var binding: FragmentNewsBinding

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
        viewModel = (activity as MainActivity).viewModel

        setupRecyclerView()

//        Observe article changes and save within room
        viewModel.news.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->

                        val firstArticle = newsResponse.articles.firstOrNull()

                        firstArticle?.let {
                            viewModel.saveArticle(it)
                        }
                        adapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occurred: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }

//      save and get saved articles
        viewModel.savedArticles.observe(viewLifecycleOwner, Observer { article ->
            adapter.differ.submitList(article)
        })
        if (Utils.isNetworkAvailable(requireContext())) {
            viewModel.getNews("us")
        } else {
            showSnackbar("No internet connection. Displaying saved news")
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




