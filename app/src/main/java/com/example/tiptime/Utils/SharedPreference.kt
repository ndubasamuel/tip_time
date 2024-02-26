//package com.example.tiptime.Utils
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.content.SharedPreferences
//import android.os.Build
//import androidx.annotation.RequiresApi
//import java.util.Base64
//
//object SharedPreference {
//
//    private const val PREF_NAME = "My preferences"
//    private const val KEY_API = "api_Key"
//    private const val KEY_BASE_URL = "base_url"
//
//
//    fun saveApiAndBaseUrl(context: Context, apiKey: String, baseUrl: String) {
//        val encryptedApiKey = encrypt(apiKey)
//        val encryptedBaseUrl = encrypt(baseUrl)
//
//
//        val editor: SharedPreferences.Editor =
//            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit()
//
//        editor.putString(KEY_API, encryptedApiKey)
//        editor.putString(KEY_BASE_URL, encryptedBaseUrl)
//        editor.apply()
//    }
//
//
//    fun getApiAndBaseUrl(context: Context): Pair<String, String>? {
//        val sharedPreferences =
//            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
//
//        val encryptedApiKey = sharedPreferences.getString(KEY_API, null)
//        val encryptedBaseUrl = sharedPreferences.getString(KEY_BASE_URL, null)
//
//        if (encryptedApiKey != null && encryptedBaseUrl != null) {
//            val api = decrypt(encryptedApiKey)
//            val baseUrl = decrypt(encryptedBaseUrl)
//            return Pair(api, baseUrl)
//        }
//        return null
//
//    }
//
//    private fun encrypt(input: String): String {
//        return Base64.getEncoder().encodeToString(input.toByteArray())
//    }
//
//
//    private fun decrypt(input: String): String {
//        return String(Base64.getDecoder().decode(input.toString()))
//
//    }
//}