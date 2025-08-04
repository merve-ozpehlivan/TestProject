package com.example.inventivtestcase.model

import com.google.gson.annotations.SerializedName

data class Card(
    @SerializedName("image")
    val image: String,
    @SerializedName("number")
    val number: String,
    @SerializedName("cvv")
    val cvv: String,
    @SerializedName("balance")
    val balance: Balance,
    @SerializedName("pendingBalance")
    val pendingBalance: Balance
) {
    data class Balance(
        @SerializedName("value")
        val value: String,
        @SerializedName("currency")
        val currency: String
    )
}


