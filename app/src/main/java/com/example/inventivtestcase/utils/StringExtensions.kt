package com.example.inventivtestcase.utils

fun String.maskCardNumber(): String {
    val digitsOnly = this.filter { it.isDigit() }

    if (digitsOnly.length <= 8) return this

    val visiblePart = digitsOnly.takeLast(8)
    val maskedLength = digitsOnly.length - 8
    val masked = "*".repeat(maskedLength)

    return masked + visiblePart
}
