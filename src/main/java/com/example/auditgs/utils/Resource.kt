package com.example.auditgs.utils

sealed class Resource<T> {

    data class Success<T>(
        val data: T
    ) : Resource<T>()

    data class Error<T>(

        val message: String,

        val code: Int? = null,

        val idSession: String? = null

    ) : Resource<T>()

}