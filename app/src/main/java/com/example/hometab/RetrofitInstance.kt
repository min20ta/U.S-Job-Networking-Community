package com.example.hometab

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitInstance{

        private const val BASE_URL = "http://10.0.2.2:3000/"





    var builder = OkHttpClient().newBuilder()
    var okHttpClient = builder
        .connectTimeout(1000, TimeUnit.SECONDS)   //소켓타임아웃방지
        .readTimeout(1000,TimeUnit.SECONDS)
        .writeTimeout(1000, TimeUnit.SECONDS)
        .followRedirects(false)
        .build()



        var gson=GsonBuilder().setLenient().create()

        val client = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))  //gson객체추가
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()

        fun getInstance() : Retrofit {
            return client
        }


//    private var instance: Retrofit? = null
//    private val gson = GsonBuilder().setLenient().create()
//
//
//    // timeout시간을 설정해줍니다.
//    private const val CONNECT_TIMEOUT_SEC = 20000L
//
//    fun getInstance(): Retrofit {
//        if(instance == null) {
//            val interceptor = HttpLoggingInterceptor()
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//
//            val client = OkHttpClient.Builder()
//                .cookieJar(JavaNetCookieJar(CookieManager()))
//                .addInterceptor(interceptor)
//                .connectTimeout(CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS)
//                .build()
//
//            instance = Retrofit.Builder()
//                .baseUrl(BASE_URL)
////                .addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
//                .client(client)
//                .build()
//        }
//
//        return instance!!
//    }
}




