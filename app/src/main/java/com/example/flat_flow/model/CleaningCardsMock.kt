package com.example.flat_flow.model

data class CleaningCardsMock(
    val numberDay: Int?,
    val dayOfTheWeek: String?,
    val recurrence: String,
    val assigned: String,
    val task: String,
)

val cleaningCardMock =
    listOf(
        CleaningCardsMock(
            numberDay = null,
            dayOfTheWeek = "Saturday",
            recurrence = "Weekly",
            assigned = "Susan",
            task = "Wash dishes",
        ),
        CleaningCardsMock(
            numberDay = 14,
            dayOfTheWeek = null,
            recurrence = "Monthly",
            assigned = "Boyle",
            task = "Clean windows",
        ),
        CleaningCardsMock(
            numberDay = null,
            dayOfTheWeek = "Saturday",
            recurrence = "Weekly",
            assigned = "Ronaldo",
            task = "Clean cat litter",
        ),
    )
