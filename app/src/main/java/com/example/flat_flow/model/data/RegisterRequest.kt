package com.example.flat_flow.model.data

data class RegisterRequest(
    val email: String,
    val senha: String,
)

data class RegisterResponse(
    val success: Boolean,
    val message: String?,
)
