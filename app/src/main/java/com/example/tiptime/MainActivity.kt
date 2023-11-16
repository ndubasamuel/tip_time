package com.example.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiptime.ViewAdapter.CustomAdapter
import com.example.tiptime.databinding.ActivityMainBinding
import com.example.tiptime.network.Models.NewsHeadlines

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//
//        binding.recyclerMain.apply {
//            setHasFixedSize(true)
//            layoutManager = LinearLayoutManager(this@MainActivity)//GridLayoutManager(requireContext(), 1)
//            adapter = CustomAdapter(NewsHeadlines.listOfNewsHeadlines())
//        }

    }
}