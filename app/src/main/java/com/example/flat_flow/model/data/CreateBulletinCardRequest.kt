package com.example.flat_flow.model.data

data class CreateBulletinCardRequest(
    val title: String,
    val content: String,
    val republicId: String?
)