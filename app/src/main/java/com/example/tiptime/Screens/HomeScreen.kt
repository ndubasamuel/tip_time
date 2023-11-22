package com.example.tiptime.Screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tiptime.R
import com.example.tiptime.databinding.FragmentHomeScreenBinding


class HomeScreen : Fragment() {

    private lateinit var binding:FragmentHomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply{
            tipButton.setOnClickListener{
                findNavController().navigate(R.id.action_homeScreen_to_tip)
            }
            newsButton.setOnClickListener {
                findNavController().navigate(R.id.action_homeScreen_to_news)
            }

        }
    }


}