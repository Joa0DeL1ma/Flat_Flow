package com.example.flat_flow.model.data

data class RepublicEnterRequest(
    val codigo: String,
    val idUsuarios: Int
)

data class RepublicEnterResponse(
    val success: Boolean,
    val PisoCompartido_idPisoCompartido: Int,
    val message: String
)