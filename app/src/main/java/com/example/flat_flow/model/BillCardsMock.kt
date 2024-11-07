package com.example.flat_flow.model

data class BillCardsMock(
    val numberDay: Int?,
    val dayOfTheWeek: String?,
    val recurrence: String,
    val value: Double,
    val billName: String,
)

val billCardMock =
    listOf(
        BillCardsMock(
            numberDay = 4,
            dayOfTheWeek = null,
            recurrence = "Monthly",
            value = 555.05,
            billName = "Rent Bill",
        ),
        BillCardsMock(
            numberDay = null,
            dayOfTheWeek = "Saturday",
            recurrence = "Weekly",
            value = 859.01,
            billName = "Groceries",
        ),
        BillCardsMock(
            numberDay = 25,
            dayOfTheWeek = null,
            recurrence = "Monthly",
            value = 111.11,
            billName = "Light Bill",
        ),
    )
