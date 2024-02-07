package com.example.tiptime.Screens

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tiptime.MainActivity
import com.example.tiptime.R
import com.example.tiptime.ViewModel.NewsViewModel
import com.example.tiptime.databinding.FragmentHomeScreenBinding
import java.util.Locale

class HomeScreen : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding
    private lateinit var context: Context
    private lateinit var resources: Resources
    private var i = 0
    lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        context = requireContext()
        resources = context.resources


        binding.apply {
            tipButton.setOnClickListener {
                findNavController().navigate(R.id.action_homeScreen_to_tip)
            }
            newsButton.setOnClickListener {
                findNavController().navigate(R.id.action_homeScreen_to_news)

            }

            btnChangeLang.setOnClickListener {
                showChangedLang()
            }

        }
    }

    private fun showChangedLang() {

        val listItems = arrayOf("ENGLISH", "SWAHILI")
        val mBuilder = AlertDialog.Builder(context)
        mBuilder.setTitle("Choose Language")
            .setSingleChoiceItems(listItems, -1) { dialog, which ->
                when (which) {
                    0 -> setLocate("en")
                    1 -> setLocate("sw")
                }
                dialog.dismiss()

            }
        val mDialog = mBuilder.create()
        mDialog.show()
    }

    private fun setLocate(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration()
        config.locale = locale

        context.resources.updateConfiguration(config, context.resources.displayMetrics)

        saveLanguageToSharedPreferences(languageCode)
    }

    private fun saveLanguageToSharedPreferences(languageCode: String) {
        val editor: SharedPreferences.Editor = context.getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", languageCode)
        editor.apply()


        }

}
