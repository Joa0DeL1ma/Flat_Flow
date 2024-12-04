package com.example.flat_flow.model.data

data class LoginRequest(
    val email: String,
    val senha: String,
)

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val userId: Int,
    val isAdmin: Boolean,
    val republica: Int
)
