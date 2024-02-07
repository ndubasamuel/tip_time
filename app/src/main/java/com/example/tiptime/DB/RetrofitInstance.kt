package com.example.tiptime.DB


import com.example.tiptime.API.NewsApi
import com.example.tiptime.Utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        }

        val api by lazy {
            retrofit.create(NewsApi::class.java)
        }
    }

}


//    internal fun provideGson(): Gson {
//        val gsonBuilder = GsonBuilder()
//        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//        gsonBuilder.setDateFormat("yyy-mm-dd")
//        return gsonBuilder.create()
//    }
//
//    internal fun provideOkHttpClient(cache: Cache, application: Application): OkHttpClient {
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        return OkHttpClient.Builder()
//            .readTimeout(30, TimeUnit.SECONDS)
//            .connectTimeout(15, TimeUnit.SECONDS)
//            .writeTimeout(60, TimeUnit.SECONDS)
//            .addInterceptor(interceptor)
//            .cache(cache)
//            .build()
//    }
//
//    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//            .addConverterFactory(ScalarsConverterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .baseUrl(BASE_URL)
//            .client(okHttpClient)
//            .build()
//    }
//
//
//    internal fun getNewsApi(retrofit: Retrofit): NewsApi {
//        return retrofit.create(NewsApi::class.java)
//    }
//}


//    @Provides
//    @Singleton
//    internal fun provideHttCache(application: Application): Cache {
//        val cacheSize = 10 * 1024 * 1024
//        return Cache(application.applicationContext.cacheDir, cacheSize.toLong())
//    }



//    private fun loggingInterceptor():HttpLoggingInterceptor
//    {
//        val logging = HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        return logging;
//    }
//    private fun okHttp(): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor())
//            .build()



//    private val client = OkHttpClient.Builder().build()
//    private val retrofit = Retrofit.Builder()
//        .baseUrl("https://newsapi.org/v2/")
//        .addConverterFactory((GsonConverterFactory.create()))
//        .client(okHttp())
//        .client(getPinnedOkHttpClient())
//        .build()




//    fun<T> builderService(service: Class<T>):T {
//        return retrofit.create(service)
//    }
