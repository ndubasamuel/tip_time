package com.example.tiptime.DB


import android.app.Application
import android.content.Context
import com.example.tiptime.API.NewsApi
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton


@Module
class RetrofitInstance @Inject constructor(private val context: Context) {
    private external fun getBaseUrlFromNative(): String
    private external fun getApiFromNative(): String
    init {

        System.loadLibrary("native-lib")

    }
    private val apiKey = getApiFromNative()
    private val baseUrl = getBaseUrlFromNative()
//    private val baseUrl = "https://newsapi.org/"



        @Provides
    @Singleton
    internal fun provideHttCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.applicationContext.cacheDir, cacheSize.toLong())
    }
        @Provides
        @Singleton
        internal fun provideGson(): Gson {
            val gsonBuilder = GsonBuilder()
            gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            gsonBuilder.setDateFormat("yyy-mm-dd")
            return gsonBuilder.create()
        }

        @Provides
        @Singleton
        internal fun provideOkHttpClient(cache: Cache): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor{ chain ->
                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                        .header("apiKey", apiKey)
                        .method(original.method, original.body)
                    val request = requestBuilder.build()
                    chain.proceed(request)
                    
                }
                .cache(cache)
                .build()
        }

        @Provides
        @Singleton
        internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build()
        }

        @Provides
        @Singleton
        internal fun provideNewsApi(retrofit: Retrofit): NewsApi {
            return retrofit.create(NewsApi::class.java)
        }


}