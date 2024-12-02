package com.example.flat_flow.model.data.api

import com.example.flat_flow.domain.BillCards
import com.example.flat_flow.domain.BulletinCards
import com.example.flat_flow.domain.CleaningCards
import com.example.flat_flow.model.data.LoginRequest
import com.example.flat_flow.model.data.LoginResponse
import com.example.flat_flow.model.data.RegisterRequest
import com.example.flat_flow.model.data.RegisterResponse
import com.example.flat_flow.model.data.RepublicEnterRequest
import com.example.flat_flow.model.data.RepublicEnterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("usuarios/login")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): Response<LoginResponse>

    @POST("usuarios/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest,
    ): Response<RegisterResponse>

    @POST("republicEnter")
    suspend fun republicEnter(
        @Body republicEnterRequest: RepublicEnterRequest,
    ): Response<RepublicEnterResponse>

    @GET("bulletinCards")
    suspend fun getBulletinCards(@Query("republica") republica: String): List<BulletinCards>

    @GET("billCards")
    suspend fun getBillCards(@Query("republica") republica: String): List<BillCards>

    @GET("cleaningCards")
    suspend fun getCleaningCards(@Query("republica") republica: String): List<CleaningCards>

}


