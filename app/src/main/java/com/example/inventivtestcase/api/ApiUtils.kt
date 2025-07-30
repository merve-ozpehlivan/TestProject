package com.example.inventivtestcase.api

object ApiUtils {
    private const val BASE_URL = "https://0565fb8b-cd64-4df3-ae54-8decf213404b.mock.pstmn.io/"

    fun getCardsDaoInterface(): CardsDaoInterface {
        return RetrofitClient.getClient(BASE_URL).create(CardsDaoInterface::class.java)
    }
}
