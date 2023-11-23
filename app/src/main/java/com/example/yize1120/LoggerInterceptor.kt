package com.example.yize1120

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor

var TAG: String? = "----"
fun LoggerInterceptor(): HttpLoggingInterceptor {
    val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Log.w(TAG, "log: $message")
        }
    })

    //设置打印数据的级别
    loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
    return loggingInterceptor
}

/**
 * @Author : Quzx
 * @Time : 2023/11/22 11:02
 */

/*
class LoggerInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
//        val request = chain.request()
//        request.
    }
}
*/

