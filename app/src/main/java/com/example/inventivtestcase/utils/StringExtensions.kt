package com.example.inventivtestcase.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun String.maskCardNumber(): String {
    val numbers = filter(Char::isDigit)
    return if (numbers.length >= 8) {
        "${numbers.take(4)} **** **** ${numbers.takeLast(4)}"
    } else this
}

fun String.formatAsTLFromMinorUnit(): String {
    val kurus = toLongOrNull() ?: return this
    val lira = kurus / 100.0

    val formatSymbols = DecimalFormatSymbols(Locale("tr", "TR")).apply {
        groupingSeparator = '.'
        decimalSeparator = ','
    }

    val formatter = DecimalFormat("#,##0.00", formatSymbols)
    return "${formatter.format(lira)} TL"
}

fun String.formatCardNumber(): String =
    chunked(4).joinToString(" ")