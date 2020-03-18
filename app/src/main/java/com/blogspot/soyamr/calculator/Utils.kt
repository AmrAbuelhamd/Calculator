package com.blogspot.soyamr.calculator

import android.graphics.Color
import android.widget.TextView
import androidx.core.content.ContextCompat

object Utils {
    fun isOperator(ch: Char) =
        ch == '/' || ch == '*' || ch == '-' || ch == '+'

    fun removeTheLastOperatorIfExists(exep: String) =
        if (isOperator(exep.last()))
            exep.dropLast(1)
        else exep


    //split the expresion to parts
// from 9-7/7 to [9, -, 7, /, 7]
    fun splitTheInput(exep: String) = exep.split(Regex("(?<=[\\-/+*()])|(?=[\\-/+*()])"))
        .filter { it.isNotEmpty() }

    fun swapSize(resultTextView: TextView, userInputTextView: TextView) {
        resultTextView.textSize = 90f
        userInputTextView.textSize = 30f
        resultTextView.setTextColor(Color.parseColor("#000000"))
        userInputTextView.setTextColor(Color.parseColor("#A9A9A9"))
    }


}
