package com.example.inventivtestcase.api

import com.example.inventivtestcase.model.Card
import retrofit2.http.GET

interface CardsDaoInterface {
    @GET("wallets")
    suspend fun getCards(): List<Card>
}
