package com.example.flat_flow.model.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://f2b0f67c-1606-4384-a575-43c0b247e71c.mock.pstmn.io/" // URL da sua API

    // Função para criar o Retrofit com log
    val api: ApiService by lazy {
        // Criação do interceptor de log
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY // Nível de log (pode ser Level.BODY, Level.HEADERS ou Level.BASIC)

        // Criação do OkHttpClient com o interceptor de log
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)  // Adiciona o interceptor de log
            .build()

        // Criação do Retrofit com o OkHttpClient configurado
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)  // Passa o OkHttpClient com o interceptor para o Retrofit
            .addConverterFactory(GsonConverterFactory.create())  // Converte a resposta JSON para objetos Kotlin
            .build()
            .create(ApiService::class.java)
    }
}
