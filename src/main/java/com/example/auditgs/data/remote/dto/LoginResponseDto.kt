package com.example.auditgs.data.remote.dto

data class LoginResultDto(
    val correo: String,
    val idPerfil: Int,
    val idPerfilJefe: String,
    val idSession: String,
    val nivel: Int,
    val nombre: String,
    val nombreJefe: String,
    val numeroEmpleado: String,
    val numeroEmpleadoJefe: String,
    val perfil: String,
    val perfilJefe: String,
    val permisos: List<PermisoDto>,
    val respuesta: Int,
    val token: String
)
