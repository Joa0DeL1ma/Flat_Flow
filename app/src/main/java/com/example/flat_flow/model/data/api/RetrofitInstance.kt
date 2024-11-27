package com.example.flat_flow.model.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://f2b0f67c-1606-4384-a575-43c0b247e71c.mock.pstmn.io/" //"https://shrouded-beyond-77157-bd283c70c14e.herokuapp.com/api/" // Substitua pelo URL da sua API

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
