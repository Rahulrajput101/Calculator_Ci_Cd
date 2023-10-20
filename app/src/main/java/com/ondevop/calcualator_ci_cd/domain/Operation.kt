package com.ondevop.calcualator_ci_cd.domain

enum class Operation(val symbol: Char) {
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('x'),
    DIVIDE('/'),
    PERCENT('%')
}

val operationSymbol = Operation.values().map { it.symbol }.joinToString("")

fun operationFromSymbol(symbol: Char)  :Operation{
    return Operation.values().find { it.symbol == symbol }
        ?: throw IllegalAccessException("Invalid symbol")
}

