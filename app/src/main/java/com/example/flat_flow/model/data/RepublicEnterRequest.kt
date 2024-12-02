package com.example.flat_flow.model.data

data class RepublicEnterRequest(
    val republica: String
)

data class RepublicEnterResponse(
    val success: Boolean,
    val message: String
)