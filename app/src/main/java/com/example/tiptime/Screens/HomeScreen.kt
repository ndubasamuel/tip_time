package com.example.tiptime.Screens

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tiptime.R
import com.example.tiptime.databinding.FragmentHomeScreenBinding
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import androidx.core.app.ActivityCompat.recreate
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.language.bm.Lang
import java.util.Locale

class HomeScreen : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding
    private lateinit var context: Context
    private lateinit var resources: Resources

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
        context = requireContext()
        resources = context.resources

        // Your existing business logic

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

        private fun loadLocate() {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
            val language = sharedPreferences.getString("My_Lang", "")
            setLocate(language ?: "")
        }
}
