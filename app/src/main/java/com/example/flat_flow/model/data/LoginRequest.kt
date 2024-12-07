package com.example.flat_flow.model.data

data class LoginRequest(
    val email: String,
    val contraseña: String,
)

data class LoginResponse(
    val success: Boolean,
    val idUsuarios: Int,
    val PisoCompartido_idPisoCompartido: Int,
    val codigo: String,
    val message: String
)
