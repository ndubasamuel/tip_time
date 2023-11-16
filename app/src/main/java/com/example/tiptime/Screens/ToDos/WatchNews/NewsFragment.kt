package com.example.tiptime.Screens.ToDos.WatchNews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiptime.R
import com.example.tiptime.ViewAdapter.CustomAdapter
import com.example.tiptime.databinding.FragmentNewsBinding
import com.example.tiptime.network.Models.NewsApiResponse
import com.example.tiptime.network.Models.NewsHeadlines
import com.example.tiptime.network.Models.OnFetchDataListener
import com.example.tiptime.network.Models.RequestManager


class NewsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomAdapter
    private var newsList: List<NewsHeadlines> = emptyList()
private  lateinit var  binding: FragmentNewsBinding
     //Listener
    private val listener = object : OnFetchDataListener<NewsApiResponse> {
        override fun onFetchData(list: List<NewsHeadlines>, message: String) {
            updateNewsList(list)
        }
        override fun onError(message: String) {
            println("error!")
        }
    }


    private fun updateNewsList(newList: List<NewsHeadlines>) {
        newsList = newList
        adapter.updateData(newsList)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fetchNewsData()
    }
    //On create view
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val view = inflater.inflate(R.layout.fragment_news, container, false)
        //recyclerView = view.findViewById<RecyclerView>(R.id.recycler_main)
        //view.findViewById<TextView>(R.id.tvTitle).text = "Trying"
        binding = FragmentNewsBinding.inflate(layoutInflater)


//        Log.d("about to fetch news", "calling the fetchNewsData method")
        fetchNewsData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CustomAdapter(NewsHeadlines.listOfNewsHeadlines())
        binding.recyclerMain.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity())//GridLayoutManager(requireContext(), 1)
            //adapter = CustomAdapter(NewsHeadlines.listOfNewsHeadlines())
            adapter = CustomAdapter(newsList)
        }

    }

    private fun fetchNewsData() {
        val manager = RequestManager(requireContext())
//        Log.d("fetching news done here", "fetchNewsData: ${manager.getNewsHeadlines(listener,"",null)} ")
        manager.getNewsHeadlines(listener, "general", null)
    }

}



