package com.example.flat_flow.model.data.api

import com.example.flat_flow.domain.BillCards
import com.example.flat_flow.domain.BulletinCards
import com.example.flat_flow.domain.CleaningCards
import com.example.flat_flow.model.data.CreateBillCardRequest
import com.example.flat_flow.model.data.CreateBillCardResponse
import com.example.flat_flow.model.data.CreateBulletinCardRequest
import com.example.flat_flow.model.data.CreateBulletinCardResponse
import com.example.flat_flow.model.data.CreateCleaningCardRequest
import com.example.flat_flow.model.data.CreateCleaningCardResponse
import com.example.flat_flow.model.data.DeleteBillCardRequest
import com.example.flat_flow.model.data.DeleteBulletinCardRequest
import com.example.flat_flow.model.data.DeleteCleaningCardRequest
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
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("login_usuario")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): Response<LoginResponse>

    @POST("criar_usuario")
    suspend fun register(
        @Body registerRequest: RegisterRequest,
    ): Response<RegisterResponse>

    @POST("entrar_republica")
    suspend fun republicEnter(
        @Body republicEnterRequest: RepublicEnterRequest,
    ): Response<RepublicEnterResponse>

    @POST("criar_republica")
    suspend fun republicCreate(
        @Body republicCreateRequest: RepublicCreateRequest,
    ): Response<RepublicCreateResponse>

    @POST("createBillCard")
    suspend fun createBillCard(
        @Body createBillCardRequest: CreateBillCardRequest,
    ): Response<CreateBillCardResponse>

    @POST("createCleaningCard")
    suspend fun createCleaningCard(
        @Body createCleaningCardRequest: CreateCleaningCardRequest,
    ): Response<CreateCleaningCardResponse>

    @POST("createBulletinCard")
    suspend fun createBulletinCard(
        @Body createBulletinCardRequest: CreateBulletinCardRequest,
    ): Response<CreateBulletinCardResponse>

    @DELETE("deleteBulletinCard")
    suspend fun deleteBulletinCard(
        @Query("informaciones") informaciones: String,
        @Query("PisoCompartido_idPisoCompartido") PisoCompartido_idPisoCompartido: Int
    ): Response<Unit>

    @DELETE("deleteBillCard")
    suspend fun deleteBillCard(
        @Query("compra") compra: String,
        @Query("PisoCompartido_idPisoCompartido") PisoCompartido_idPisoCompartido: Int
    ): Response<Unit>

    @DELETE("deleteCleaningCard")
    suspend fun deleteCleaningCard(
        @Query("quehacer") quehacer: String,
        @Query("PisoCompartido_idPisoCompartido") PisoCompartido_idPisoCompartido: Int
    ): Response<Unit>

    @GET("getBulletinCard")
    suspend fun getBulletinCards(@Query("PisoCompartido_idPisoCompartido") PisoCompartido_idPisoCompartido: Int): List<BulletinCards>

    @GET("getBillCard")
    suspend fun getBillCards(@Query("PisoCompartido_idPisoCompartido") PisoCompartido_idPisoCompartido: Int): List<BillCards>

    @GET("getCleaningCard")
    suspend fun getCleaningCards(@Query("PisoCompartido_idPisoCompartido") PisoCompartido_idPisoCompartido: Int): List<CleaningCards>

}


