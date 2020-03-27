package com.blogspot.soyamr.calculator.utils

import java.util.*

object Infix {
    //the expression is guaranteed to have a balanced brackets, clean of additional operations at the end
    // or mistakes like +*-,
    // underdeveleopment -> also it might have - at the beginning guaranteeing that the first number is minus
    // the algorithm handles brackets situations but they must be balanced and in the right place
    fun inf2postF(expression: String): Double {
        val postfix: Deque<Double> = LinkedList()//queue
        val operatorList: Deque<String> = LinkedList()
        var popped: String
        val listOfExepressionParts = Utils.splitTheInput(expression)
        for (current in listOfExepressionParts) {

            if (current == ")") {
                popped = operatorList.removeLast()
                while (popped != "(") {
                    postfix.addLast(
                        makeTheOperation(
                            popped,
                            postfix.removeLast(),
                            postfix.removeLast()
                        )
                    )
                    popped = operatorList.removeLast()
                }

            } else if (!isOperator(
                    current
                )
            )
                postfix.add(current.toDouble())
            else {
                while (operatorList.isNotEmpty()
                    && current != "("
                    && precedence(
                        operatorList.last()
                    ) >= precedence(
                        current
                    )
                ) {
                    postfix.add(
                        makeTheOperation(
                            operatorList.removeLast(),
                            postfix.removeLast(),
                            postfix.removeLast()
                        )
                    )
                }
                operatorList.add(current)
            }

        }
        while (operatorList.isNotEmpty())
            postfix.add(
                makeTheOperation(
                    operatorList.removeLast(),
                    postfix.removeLast(),
                    postfix.removeLast()
                )
            )

        return postfix.removeLast()

    }

    private fun isOperator(current: String) = precedence(current) > 0

    private fun precedence(current: String) =
        when (current) {
            "(", ")" -> 1
            "-", "+" -> 2
            "*", "/" -> 3
            else -> 0
        }

    private fun makeTheOperation(op: String, a: Double, b: Double) =
        when (op) {
            "+" -> a + b
            "-" -> b - a
            "/" -> b / a
            "*" -> a * b
            else -> throw IllegalArgumentException("something tribally went wrong")
        }
}