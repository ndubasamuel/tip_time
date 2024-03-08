package com.example.tiptime

import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


class MainActivity : AppCompatActivity() {


    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result: ActivityResult ->
            if (result.resultCode != RESULT_OK) {
                Log.w("First Activity", "Update flow failed! Result Code: ${result.resultCode}")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkForUpdates()


//        val retrofit: Retrofit = Retrofit.Builder()
//            .baseUrl("https://developer.android.com/get-started/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        // Create an instance of our GitHub API interface.
//
//        // Create an instance of our GitHub API interface.
//        val github = retrofit.create(GitHub::class.java)
//
//        // Create a call instance for looking up Retrofit contributors.
//
//        // Create a call instance for looking up Retrofit contributors.
//
//
//        Thread {
//            val call: Call<String> = github.contributors()
//            val contributors: String? = call!!.execute().body()
//        }.start()

    }

    //    Handling in-app updates
    private fun checkForUpdates() {
        val appUpdateManager = AppUpdateManagerFactory.create(this)
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo

        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                try {
                        appUpdateManager.startUpdateFlowForResult(
                            appUpdateInfo,
                            activityResultLauncher,
                                    AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE)
                                .setAllowAssetPackDeletion(true)
                                .build(),

                        )
                } catch (e: IntentSender.SendIntentException) {
                    throw RuntimeException(e)
                }
            }
        }
    }
}

//interface GitHub {
//    @GET("overview")
//    fun contributors(
////        @Path("owner") owner: String?,
////        @Path("repo") repo: String?
//    ): Call<String>
//}





