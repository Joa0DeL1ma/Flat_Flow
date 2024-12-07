package com.example.flat_flow.model.data

data class CreateBulletinCardRequest(
    val informaciones: String,
    val Usuario_idUsuarios: Int,
    val PisoCompartido_idPisoCompartido: Int
)

data class CreateBulletinCardResponse(
    val success: Boolean,
    val message: String
)