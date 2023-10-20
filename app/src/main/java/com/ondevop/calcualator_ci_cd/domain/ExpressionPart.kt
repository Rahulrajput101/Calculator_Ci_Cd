package com.ondevop.calcualator_ci_cd.domain

sealed interface ExpressionPart{
    data class Numbers(val numbers : Double): ExpressionPart
    data class Op(val operation: Operation): ExpressionPart
    data class Parenthesis(val type: ParenthesisType) : ExpressionPart
}

sealed interface ParenthesisType{
    object Opening: ParenthesisType
    object Closing: ParenthesisType
}
