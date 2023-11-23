package com.example.yize1120

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

/**
 * @Author : Quzx
 * @Time : 2023/11/21 17:07
 */
class CoroutinesViewModel: ViewModel() {
    val api by lazy { RetrofitManager.getApiService() }
    var articlesLiveData: MutableLiveData<MutableList<Data>> = MutableLiveData()

    var apiError: MutableLiveData<Throwable> = MutableLiveData()

    fun getArticles(page: Int){
        val exception = CoroutineExceptionHandler { coroutineContext, throwable ->
            apiError.postValue(throwable)
            Log.i("CoroutinesViewModel",throwable.message!!)
        }

        viewModelScope.launch {
            val response = api.getArticleList(page)
            Log.e(TAG, "getArticles: ${response.data}")
            Log.e("data.data", "getArticles: ${response.data?.datas}")
            if (response.info == 0) {
                articlesLiveData.postValue(response.data?.datas)
            }else{
                articlesLiveData.postValue(mutableListOf())
            }
        }

    }
}