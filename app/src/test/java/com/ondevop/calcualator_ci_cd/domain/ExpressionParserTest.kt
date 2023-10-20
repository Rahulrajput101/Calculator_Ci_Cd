package com.ondevop.calcualator_ci_cd.domain


import org.junit.Test
import com.google.common.truth.Truth.assertThat

class ExpressionParserTest {

    private lateinit var parser : ExpressionParser

    @Test
    fun `Simple expression is properly parsed`(){

        // 1.Given
        parser = ExpressionParser("3+5-3x4/3")


        // 2. Do something with what's given
        val actual = parser.parse()

        //3. Assert expected == actual
        val expected = listOf(
            ExpressionPart.Numbers(3.0),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Numbers(5.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Numbers(3.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Numbers(4.0),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Numbers(3.0),
        )

        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `Expression with parentheses is properly parsed` () {
        parser = ExpressionParser("4-(4x5)")

        val actual = parser.parse()

        val expected = listOf(
            ExpressionPart.Numbers(4.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Parenthesis(ParenthesisType.Opening),
            ExpressionPart.Numbers(4.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Numbers(5.0),
            ExpressionPart.Parenthesis(ParenthesisType.Closing),
        )

        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `Complex expression with multiple parenthesis parsed` (){
        parser = ExpressionParser("2+((5.2x30)-3.5)")

        val actual = parser.parse()

        val expected = listOf(
            ExpressionPart.Numbers(2.0),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Parenthesis(ParenthesisType.Opening),
            ExpressionPart.Parenthesis(ParenthesisType.Opening),
            ExpressionPart.Numbers(5.2),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Numbers(30.0),
            ExpressionPart.Parenthesis(ParenthesisType.Closing),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Numbers(3.5),
            ExpressionPart.Parenthesis(ParenthesisType.Closing),
        )

        assertThat(expected).isEqualTo(actual)
    }


}