package com.example.flat_flow.model.data

import java.util.Date

data class CreateBillCardRequest(
    val valor: String,
    val diaVencimiento: String,
    val compra: String,
    val PisoCompartido_idPisoCompartido: Int
)

data class CreateBillCardResponse(
    val success: Boolean,
    val message: String
)