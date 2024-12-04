package com.example.flat_flow.model.data

data class RepublicEnterRequest(
    val idRepublica: Int
)

data class RepublicEnterResponse(
    val success: Boolean,
    val message: String
)