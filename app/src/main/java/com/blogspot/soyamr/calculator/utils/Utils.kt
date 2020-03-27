package com.blogspot.soyamr.calculator.utils

object Utils {

    fun isOperator(ch: Char) =
        ch == '/' || ch == '*' || ch == '-' || ch == '+'

    fun removeTheLastOperatorIfExists(exep: String): String {
        var ctr = "zer";
        var ee = exep
        if (exep.last() == '(') {
            ee = ee.dropLast(1)
            ctr = "one"
        }
        if (isOperator(ee.last()))
            ee = ee.dropLast(1)
        return ee.plus(ctr)
    }

    //split the expresion to parts
    // from 9-7/7 to [9, -, 7, /, 7]
    fun splitTheInput(exep: String) = exep.split(Regex("(?<=[\\-/+*()])|(?=[\\-/+*()])"))
        .filter { it.isNotEmpty() }

}
