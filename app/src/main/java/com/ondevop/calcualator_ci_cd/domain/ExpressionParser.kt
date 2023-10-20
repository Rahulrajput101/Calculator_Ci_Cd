package com.ondevop.calcualator_ci_cd.domain

class ExpressionParser(
    val calculation : String
) {


    fun parse() : List<ExpressionPart> {

        val result  = mutableListOf<ExpressionPart>()
        var i = 0
        while (i < calculation.length){
            val cuChar = calculation[i]

            when{
                cuChar in operationSymbol  -> {
                    result.add(
                        ExpressionPart.Op(operationFromSymbol(cuChar))
                    )
                }

                cuChar.isDigit() -> {
                    i = parseNumber(i,result)
                    continue
                }

                cuChar in "()" -> {
                    parseParenthesis(cuChar ,result)
                }
            }
            i++
        }
        return result
    }

    private fun parseNumber(startingIndex: Int, result: MutableList<ExpressionPart>): Int {
        var  i = startingIndex
         val numberAsString = buildString {
             while ( i < calculation.length && calculation[i] in "0123456789.")  {
                 append(calculation[i])
                 i++
             }
         }
        result.add(ExpressionPart.Numbers(numberAsString.toDouble()))
        return i
    }

    private fun parseParenthesis(cuChar: Char, result: MutableList<ExpressionPart>) {
        result.add(
            ExpressionPart.Parenthesis(
                type = when(cuChar){
                     '(' -> ParenthesisType.Opening
                     ')' -> ParenthesisType.Closing
                     else -> {
                         throw IllegalArgumentException("Invalid parenthesis type")
                     }
                }
            )
        )

    }

}