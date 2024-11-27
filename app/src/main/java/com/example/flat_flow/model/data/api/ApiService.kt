package com.example.flat_flow.model.data.api

import com.example.flat_flow.domain.BillCards
import com.example.flat_flow.domain.BulletinCards
import com.example.flat_flow.model.data.LoginRequest
import com.example.flat_flow.model.data.LoginResponse
import com.example.flat_flow.model.data.RegisterRequest
import com.example.flat_flow.model.data.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("usuarios/login")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): Response<LoginResponse>

    @POST("usuarios/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest,
    ): Response<RegisterResponse>

    @GET("bulletinCards")
    suspend fun getBulletinCards(): List<BulletinCards>

    @GET("billCards")
    suspend fun getBillCards(): List<BillCards>

}


