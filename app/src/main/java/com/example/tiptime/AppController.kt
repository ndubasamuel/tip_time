package com.example.tiptime

import android.app.Application
import com.example.tiptime.DB.RetrofitInstance

class AppController: Application() {

//    lateinit var mApiComponent: ApiComponent
     override fun onCreate() {
         super.onCreate()
         app = this


//         mApiComponent = DaggerApiComponent.builder()
//             .appModule(AppModule(this))
//             .apiHelper(RetrofitInstance())
//             .dBModule(DBModule(this))
//             .build()

     }
    companion object {
        lateinit var app: AppController
        private set

    }



}