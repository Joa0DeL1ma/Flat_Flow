package com.example.flat_flow.model.data

data class CreateBillCardRequest(
    val numberDay: String?,
    val dayOfTheWeek: String?,
    val recurrence: String,
    val value: String,
    val billName: String,
    val republicId: String?
)