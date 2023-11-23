package com.example.yize1120

import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @Author : Quzx
 * @Time : 2023/11/21 14:50
 */
interface AppApi {
    // 和协程连用
    @GET("article/list/{page}/json")
    suspend fun getArticleList(@Path("page") page:Int): Response<ArticleListBean>
}