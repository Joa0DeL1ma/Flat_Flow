package com.example.flat_flow.domain

import java.util.Date

data class BillCards(
    val diaVencimiento: Date,
    val valor: String,
    val compra: String
)
