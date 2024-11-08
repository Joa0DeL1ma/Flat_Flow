package com.example.flat_flow.model.data

data class LoginRequest(
    val email: String,
    val password: String,
)

data class LoginResponse(
    val success: Boolean,
    val message: String,
)
