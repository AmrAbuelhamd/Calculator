package com.blogspot.soyamr.calculator.presenter


import com.blogspot.soyamr.calculator.model.CalculatorData
import com.blogspot.soyamr.calculator.utils.Utils
import com.blogspot.soyamr.calculator.view.ViewParent

class PresenterMainActivity(private val mainActivityView: ViewParent) : Presenter {

    //cool thing to be able to add funciton to the class from outside and call it as if it was part
    // of the class originally
    fun CalculatorData.append(value: String) {
        userInput = value
    }

    fun CalculatorData.replaceConetent(value: String) {
        userInput = CalculatorData.CLEAR
        userInput = value
    }

    private val data = CalculatorData()

    override fun OnNumButtonClick(number: String) {
        if (data.userInput.endsWith(")"))
            return
        data.append(number)
        data append number //infix way of calling function
        mainActivityView.showChangesToUser(data.userInput, data.results)
    }

    override fun onOperatorButtonClick(operatorSign: String) {

        if (data.userInput.isEmpty() || data.userInput.endsWith(operatorSign)
            || data.userInput.endsWith(CalculatorData.OPENINGBRACKET)
        )
            return

        if (Utils.isOperator(data.userInput.takeLast(1)))
            data.userInput = CalculatorData.REMOVE_LAST_CHAR

        data.userInput = operatorSign

        mainActivityView.showChangesToUser(data.userInput, data.results)
    }

    override fun onAcButtonClick() {
        data.userInput = CalculatorData.CLEAR
        data.stack.clear()
        mainActivityView.showChangesToUser(data.userInput, data.results)
    }

    override fun onDelButtonClick() {
        when (data.userInput.takeLast(1)) {
            ")" -> data.stack.addLast('(')
            "(" -> data.stack.pollLast()
        }

        data.userInput = CalculatorData.REMOVE_LAST_CHAR

        mainActivityView.showChangesToUser(data.userInput, data.results)
    }

    override fun onEqualButtonClick() {
        if (Utils.splitTheInput(data.userInput).size > 2
            && !data.results.contentEquals(CalculatorData.ERRORMESSAGE)
        ) {
            data.equalButtonWasLastClicked = true
            mainActivityView.swapColors()
        }
    }

    /*
    cases:
        (->
            - empty
            - after operation
        )->
            - number
            - i have already an opening bracket
     */
    override fun onBrackButtonClicked() {

        if (Utils.isOperator(data.userInput.takeLast(1)) || data.userInput.isEmpty())
            addOpeningBracket()
        else if (data.stack.isNotEmpty()
            && Utils.splitTheInput(data.userInput)
                .last().contains(Regex("(?:\\d+[.]?\\d*|\\))"))
        )
            addClosingBracket()

        mainActivityView.showChangesToUser(data.userInput, data.results)
    }

    private fun addClosingBracket() {
        data.userInput = CalculatorData.CLOSINGBRACKET.toString()
        data.stack.pollLast()
    }

    private fun addOpeningBracket() {
        data.userInput = CalculatorData.OPENINGBRACKET.toString()
        data.stack.addLast(CalculatorData.CLOSINGBRACKET)
    }

    override fun onDotButtonClick(dotSign: String) {
        if (data.userInput.takeLast(1).contains(Regex("\\d")) &&
            !Utils.splitTheInput(data.userInput).last().contains(".")
        ) {
            data.userInput = dotSign
            mainActivityView.showChangesToUser(data.userInput, data.results)
        }
    }

    override fun restoreTextViewSizes(resultTextViewText: String) {
        if (data.equalButtonWasLastClicked) {
            data.equalButtonWasLastClicked = false

            data.userInput = CalculatorData.CLEAR
            data.userInput = resultTextViewText
            data.stack.clear()

            mainActivityView.swapColors()
            mainActivityView.showChangesToUser(data.userInput, data.results)
        }
    }
}