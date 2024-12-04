package com.example.flat_flow.model.data

data class CreateCleaningCardRequest(
    val quehacer: String,
    val diaVencimiento: String,
    val Usuario_idUsuarios: Int,
    val PisoCompartido_idPisoCompartido: Int
)