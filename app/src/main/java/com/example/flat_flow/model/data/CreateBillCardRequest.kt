package com.example.flat_flow.model.data

data class CreateBillCardRequest(
    val valor: String,
    val diaVencimiento: String,
    val compra: String,
    val PisoCompartido_idPisoCompartido: Int,
    val Usuario_idUsuarios: Int
)