package com.example.tiptime.Screens.ToDos.WatchNews

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tiptime.API.NewsApi
import com.example.tiptime.Adapters.NewsListAdapter
import com.example.tiptime.AppController
import com.example.tiptime.MainActivity
import com.example.tiptime.Model.Article
import com.example.tiptime.Utils.Resource
import com.example.tiptime.Utils.Utils
import com.example.tiptime.ViewModel.NewsViewModel
import com.example.tiptime.ViewModel.NewsViewModelFactory
import com.example.tiptime.ViewModel.NewsViewModelFactory_Factory
import com.example.tiptime.ViewModel.NewsViewModel_Factory
import com.example.tiptime.databinding.FragmentNewsBinding
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class NewsFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var adapter: NewsListAdapter
    private lateinit var binding: FragmentNewsBinding

    @Inject
    lateinit var viewModelFactory: NewsViewModelFactory

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

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(NewsViewModel::class.java)

        viewModel.getNews()


//        Observe saved article changes and display
        viewModel.news.observe(viewLifecycleOwner) {

            viewModel.news.observe(viewLifecycleOwner) { resource ->
                when (resource) {
                    is Resource.Success<*> -> {
                        hideProgressBar()
                        val articles = resource.data as List<Article>?
                        articles.let {

                            adapter.differ.submitList(it)
                        }
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
//                    showSnackbar("Encountered an Error!")
                        showSnackbar("Unexpected error: News Response is null")
                    }
                }
            }
        }


//      save and get saved articles
        viewModel.savedNews.observe(viewLifecycleOwner, Observer {
            Log.i("FRAGMENT", "Recyclerview Loads")
            adapter.differ.submitList(it)
        })




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






