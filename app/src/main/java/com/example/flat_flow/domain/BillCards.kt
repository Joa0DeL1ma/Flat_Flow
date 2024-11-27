package com.example.flat_flow.domain

data class BillCards(
    val numberDay: Int,
    val dayOfTheWeek: String?,
    val recurrence: String,
    val value: Double,
    val billName: String
)
