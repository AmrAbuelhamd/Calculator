package com.blogspot.soyamr.calculator.model

import com.blogspot.soyamr.calculator.utils.Infix
import com.blogspot.soyamr.calculator.utils.Utils
import java.util.*


class CalculatorData {
    companion object {
        const val REMOVE_LAST_CHAR = "removes the last character"
        const val CLEAR = "wipes the data from the string"
        const val OPENINGBRACKET = '('
        const val CLOSINGBRACKET = ')'
        const val ERRORMESSAGE = "Bad expression"
    }

    private val _userInput = StringBuilder()
    var userInput: String
        get() = _userInput.toString()
        set(value) {
            when (value) {
                REMOVE_LAST_CHAR -> if (_userInput.isNotEmpty())
                    _userInput.deleteCharAt(_userInput.length - 1)
                CLEAR -> _userInput.clear()
                else //appends the argument
                -> _userInput.append(value)
            }
        }

    val results: String
        get() = calculateUserInput()

    var equalButtonWasLastClicked = false

    val stack = LinkedList<Char>()

    private fun calculateUserInput() =
        if (Utils.splitTheInput(userInput).size > 2) {
            val results: Double
            val cleaninput = cleanTheInput()
            try {
                results = Infix.inf2postF(cleaninput)
                if (results % 1 == 0.0) results.toString().dropLast(2) else results.toString()
            } catch (e: Exception) {
                ERRORMESSAGE
            }
        } else
            ""


    private fun cleanTheInput(): String {
        var ctr = stack.size
        var cleaninput = userInput
        val toDelete = Utils.howManyToRemove(userInput)
        if (toDelete > 1)
            --ctr;
        cleaninput = cleaninput.dropLast(toDelete)
        while (--ctr >= 0)
            cleaninput = cleaninput.plus(CLOSINGBRACKET)
        return cleaninput
    }


//    fun setUserInput(operation: myEnum, value: String?) {
//        when (operation) {
//            myEnum.REMOVElASTCHAR -> _userInput.deleteCharAt(_userInput.length - 1)
//            myEnum.APPEND -> _userInput.append(value)
//            myEnum.CLEAR -> _userInput.clear()
//        }
//    }
}