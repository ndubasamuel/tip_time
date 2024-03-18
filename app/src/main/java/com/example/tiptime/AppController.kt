package com.example.tiptime

import android.app.Application
import android.content.Context
import com.example.tiptime.DB.RetrofitInstance
import com.example.tiptime.ViewModel.NewsViewModelFactory
import com.example.tiptime.di.ApiComponent
import com.example.tiptime.di.AppModule
import com.example.tiptime.di.DaggerApiComponent
import com.example.tiptime.di.DbModule
import dagger.Provides
import dagger.android.AndroidInjection.inject
import javax.inject.Qualifier

class AppController: Application() {

    lateinit var mApiComponent: ApiComponent

     override fun onCreate() {
         super.onCreate()
         app = this


         mApiComponent = DaggerApiComponent.builder()
             .application(this)
             .retrofitInstance(RetrofitInstance())
             .dbModule(DbModule(this))
             .build()
             .apply {
                 inject(this@AppController)
             }
     }
    companion object {
        lateinit var app: AppController
        private set

    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class ApplicationContext



}