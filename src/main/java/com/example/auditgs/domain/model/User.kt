package com.example.auditgs.domain.model

data class User(
    val nombre: String,
    val correo: String,
    val token: String,
    val idSession: String,
    val numeroEmpleado: String,
)
