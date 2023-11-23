package com.example.yize1120


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Proxy
import java.util.concurrent.TimeUnit


/**
 * @Author : Quzx
 * @Time : 2023/11/21 14:48
 */
object RetrofitManager {

    var mApi: AppApi? = null

    private const val CONNECTION_TIME_OUT = 10L
    private const val READ_TIME_OUT = 10L

    var API_URL = "https://www.wanandroid.com/"

    fun getApiService(): AppApi {
        if (mApi == null) {
            synchronized(this) {
                if (mApi == null) {
                    val okHttpClient =
                        buildOkHttpClient()
                    mApi =
                        buildRetrofit(
                            API_URL,
                            okHttpClient
                        ).create(AppApi::class.java)
                }
            }
        }
        return mApi!!
    }

    private fun buildOkHttpClient(): OkHttpClient.Builder {
        val logging = LoggerInterceptor()
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .proxy(Proxy.NO_PROXY)
    }

    private fun buildRetrofit(baseUrl: String, builder: OkHttpClient.Builder): Retrofit {
        val client = builder.build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client).build()
    }

}