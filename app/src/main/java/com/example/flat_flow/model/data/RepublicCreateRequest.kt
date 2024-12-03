package com.example.flat_flow.model.data

data class RepublicCreateRequest(
    val userId: Int?
)
//todo fazer com que quando ele crie, a session faça ele se tornar admin. assim como o backend deve guardar no banco essa info.
data class RepublicCreateResponse(
    val republica: Int?
)