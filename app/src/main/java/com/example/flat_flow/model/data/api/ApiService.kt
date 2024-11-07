package com.example.flat_flow.model.data.api

import com.example.flat_flow.model.data.LoginRequest
import com.example.flat_flow.model.data.LoginResponse
import com.example.flat_flow.model.data.RegisterRequest
import com.example.flat_flow.model.data.RegisterResponse
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): Response<LoginResponse>

    @POST("register") // Ajuste o endpoint conforme necessário
    suspend fun register(
        @Body registerRequest: RegisterRequest,
    ): Response<RegisterResponse>
}
