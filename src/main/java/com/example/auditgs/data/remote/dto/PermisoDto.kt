package com.example.auditgs.data.remote.dto

data class PermisoDto(
    val codigoPermiso: String,
    val idPermiso: Int,
    val permiso: String,
    val tienePermisoPerfil: Boolean,
    val tienePermisoUsuario: Boolean
)
