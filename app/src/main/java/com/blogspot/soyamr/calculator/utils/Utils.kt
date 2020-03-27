package com.blogspot.soyamr.calculator.utils

object Utils {

    fun isOperator(ch: String) =
        ch == "/" || ch == "*" || ch == "-" || ch == "+"

    /*
    counts the number of extra operators and opening brackets
     */
    fun howManyToRemove(exep: String): Int {
        var ctr = 0
        var ee = exep
        if (exep.last() == '(') {
            ee = ee.dropLast(1)
            ctr++;
        }
        if (isOperator(ee.last().toString()))
            ctr++
        return ctr;
    }

    //split the expresion to parts
    // from 9-7/7 to [9, -, 7, /, 7]
    fun splitTheInput(exep: String) = exep.split(Regex("(?<=[\\-/+*()])|(?=[\\-/+*()])"))
        .filter { it.isNotEmpty() }

}
