package com.example.tiptime.Screens.ToDos.WatchNews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiptime.R
import com.example.tiptime.databinding.FragmentNewsBinding
import com.example.tiptime.network.Models.NewsAdapter


class NewsFragment : Fragment()   {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NewsAdapter
    private lateinit var binding: FragmentNewsBinding
    private lateinit var progressBar: ProgressBar

    private val newsViewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerMain
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = NewsAdapter(ArrayList())
        newsViewModel.fetchNews()
        recyclerView.adapter = adapter

        progressBar = binding.determinateBar
        progressBar.max = 100
        observeLoadingState()

        observeNews()
        newsViewModel.fetchNews()

        return binding.root
    }
    private fun observeLoadingState() {
        newsViewModel.loading.observe(viewLifecycleOwner) {
           isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
                progressBar.progress = 0
            }
        }
    }

    private fun observeNews() {
        newsViewModel.news.observe(viewLifecycleOwner) { newsList ->
            if (newsList != null) {
                adapter.updateData(newsList)
            }

        }

    }
}






