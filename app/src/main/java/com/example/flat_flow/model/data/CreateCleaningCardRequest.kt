package com.example.flat_flow.model.data

import java.util.Date

data class CreateCleaningCardRequest(
    val quehacer: String,
    val diaVencimiento: Date,
    val Usuario_idUsuarios: Int,
    val PisoCompartido_idPisoCompartido: Int
)
data class CreateCleaningCardResponse(
    val success: Boolean,
    val message: String
)
