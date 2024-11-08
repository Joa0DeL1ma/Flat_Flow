package com.example.flat_flow.model.data.api

import com.example.flat_flow.model.data.LoginRequest
import com.example.flat_flow.model.data.LoginResponse
import com.example.flat_flow.model.data.RegisterRequest
import com.example.flat_flow.model.data.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): Response<LoginResponse>

    @POST("register")
    suspend fun register(
        @Body registerRequest: RegisterRequest,
    ): Response<RegisterResponse>
}
