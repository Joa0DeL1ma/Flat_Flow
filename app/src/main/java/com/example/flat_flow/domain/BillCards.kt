package com.example.flat_flow.domain

data class BillCards(
    val numberDay: String?,
    val dayOfTheWeek: String?,
    val recurrence: String,
    val value: String,
    val billName: String
)
