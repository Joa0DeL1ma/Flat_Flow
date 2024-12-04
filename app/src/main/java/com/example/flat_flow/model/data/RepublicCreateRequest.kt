package com.example.flat_flow.model.data

data class RepublicCreateRequest(
    val codigo: Int,
    val userId: Int
)

data class RepublicCreateResponse(
    val success: Boolean,
    val idRepublica: Int,
    val message: String

)