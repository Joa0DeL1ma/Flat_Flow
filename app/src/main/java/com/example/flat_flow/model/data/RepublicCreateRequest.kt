package com.example.flat_flow.model.data

data class RepublicCreateRequest(
    val codigo: String,
    val idUsuarios: Int
)

data class RepublicCreateResponse(
    val success: Boolean,
    val PisoCompartido_idPisoCompartido: Int,
    val message: String
)