package com.example.flat_flow.model.data

data class QuitRepublicRequest(
    val idUsuarios: Int,
    val PisoCompartido_idPisoCompartido: Int
)

data class QuitRepublicResponse(
    val success: Boolean,
    val message: String
)