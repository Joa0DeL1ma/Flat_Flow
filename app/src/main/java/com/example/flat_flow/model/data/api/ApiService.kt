package com.example.flat_flow.model.data.api

import com.example.flat_flow.domain.BillCards
import com.example.flat_flow.domain.BulletinCards
import com.example.flat_flow.domain.CleaningCards
import com.example.flat_flow.model.data.CreateBillCardRequest
import com.example.flat_flow.model.data.CreateBulletinCardRequest
import com.example.flat_flow.model.data.CreateCleaningCardRequest
import com.example.flat_flow.model.data.LoginRequest
import com.example.flat_flow.model.data.LoginResponse
import com.example.flat_flow.model.data.RegisterRequest
import com.example.flat_flow.model.data.RegisterResponse
import com.example.flat_flow.model.data.RepublicCreateRequest
import com.example.flat_flow.model.data.RepublicCreateResponse
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

    @POST("republicCreate")
    suspend fun republicCreate(
        @Body republicCreateRequest: RepublicCreateRequest,
    ): Response<RepublicCreateResponse>

    @POST("createBillCard")
    suspend fun createBillCard(
        @Body createBillCardRequest: CreateBillCardRequest,
    ): Response<Unit>

    @POST("createCleaningCard")
    suspend fun createCleaningCard(
        @Body createCleaningCardRequest: CreateCleaningCardRequest,
    ): Response<Unit>

    @POST("createBulletinCard")
    suspend fun createBulletinCard(
        @Body createBulletinCardRequest: CreateBulletinCardRequest,
    ): Response<Unit>

    @GET("bulletinCards")
    suspend fun getBulletinCards(@Query("idRepublica") idRepublica: Int): List<BulletinCards>

    @GET("billCards")
    suspend fun getBillCards(@Query("idRepublica") idRepublica: Int): List<BillCards>

    @GET("cleaningCards")
    suspend fun getCleaningCards(@Query("idRepublica") idRepublica: Int): List<CleaningCards>

}


