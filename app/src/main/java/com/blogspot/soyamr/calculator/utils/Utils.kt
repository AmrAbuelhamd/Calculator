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
    fun splitTheInput(exep: String): List<String> {
        var list = exep.split(Regex("(?<=[\\-/+*()])|(?=[\\-/+*()])"))
            .filter { it.isNotEmpty() }
        //handle cases when first number in the expression is minus, since the Regex will split it
        //by merging the - with the digit
        if (list.isNotEmpty() && list.first() == "-") {
            list = list.drop(1).toMutableList()
            list[0] = "-".plus(list[0])
        }
        return list
    }

}
