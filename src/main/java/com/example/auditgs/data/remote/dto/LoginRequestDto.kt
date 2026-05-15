package com.example.auditgs.data.remote.dto

data class LoginRequestDto(
    val usuario: String,
    val dispositivo: String,
    val ip: String,
    val transferir: Boolean = false,
    val token: String = "GUID",
    val clave: String
)
