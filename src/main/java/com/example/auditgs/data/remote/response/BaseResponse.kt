package com.example.auditgs.data.remote.response

data class BaseResponse<T>(
    val codigo: Int,
    val mensaje: String,
    val resultado: T?
)
