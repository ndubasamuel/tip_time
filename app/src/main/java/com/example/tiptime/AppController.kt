package com.example.tiptime

import android.app.Application

class AppController: Application() {
    val app = this

    companion object {
        lateinit var app: AppController
            private set
    }
}
//
//    lateinit var mApiComponent: ApiComponent
//     override fun onCreate(){
//         super.onCreate()
//        app = this
//
//
//
//         mApiComponent = DaggerApiComponent.builder()
//             .appModule(AppModule(this))
//             .apiHelper(ApiHelperModule())
//             .dBModule(DBModule(this))
//             .build()
//
//     }

//
//
//}