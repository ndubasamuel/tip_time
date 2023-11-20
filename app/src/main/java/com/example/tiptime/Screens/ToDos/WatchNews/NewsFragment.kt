package com.example.tiptime.Screens.ToDos.WatchNews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiptime.databinding.FragmentNewsBinding
import com.example.tiptime.network.Models.NewsAdapter


class NewsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NewsAdapter
    private lateinit var binding: FragmentNewsBinding

    private val newsViewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerMain
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = NewsAdapter(ArrayList())
        recyclerView.adapter = adapter

        observeNews()

        return binding.root
    }

    private fun observeNews() {
        newsViewModel.news.observe(viewLifecycleOwner) { newsList ->
            if (newsList != null) {
                adapter.updateData(newsList)
            }
        }

        newsViewModel.fetchNews()
    }
}






