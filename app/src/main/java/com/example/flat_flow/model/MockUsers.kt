package com.example.flat_flow.model

data class MockUser(
    val email: String,
    val password: String,
    val admin: Boolean,
    val republic: String,
)

val mockUsers =
    listOf(
        MockUser(email = "email@email.com", password = "123456", admin = true, republic = "1234"),
        MockUser(email = "email@email.com", password = "1234567", admin = false, republic = "1234"),
        MockUser(email = "email@email.com", password = "12345678", admin = false, republic = "1234"),
        MockUser(email = "email@email.com", password = "12345678", admin = false, republic = ""),
    )
