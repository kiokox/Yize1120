package com.example.yize1120

/**
 * @Author : Quzx
 * @Time : 2023/11/21 17:02
 */
class Response<T>(
    val data: T?,
    val info: Int,
    val msg: String
)