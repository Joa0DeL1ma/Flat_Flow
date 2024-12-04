package com.example.flat_flow.model.data

data class RegisterRequest(
    val nombre: String,
    val email: String,
    val contraseña: String,
)

data class RegisterResponse(
    val success: Boolean,
    val message: String?
)
