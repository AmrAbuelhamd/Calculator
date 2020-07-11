package com.blogspot.soyamr.calculator.presenter


import com.blogspot.soyamr.calculator.model.CalculatorData
import com.blogspot.soyamr.calculator.model.Utils
import com.blogspot.soyamr.calculator.view.ViewParent

class MainPresenter(private val mainView: ViewParent) : Presenter {

    private val data = CalculatorData()

    override fun onNumButtonClick(number: String) {
        if (data.userInput.endsWith(")"))
            return
        data.userInput = number
        mainView.showChangesToUser(data.userInput, data.results)
    }

    override fun onOperatorButtonClick(operatorSign: String) {

        if (data.userInput.isEmpty() || data.userInput.endsWith(operatorSign)
            || data.userInput.endsWith(CalculatorData.OPENINGBRACKET)
        )
            return

        if (Utils.isOperator(data.userInput.takeLast(1)))
            data.userInput = CalculatorData.REMOVE_LAST_CHAR

        data.userInput = operatorSign

        mainView.showChangesToUser(data.userInput, data.results)
    }

    override fun onAcButtonClick() {
        data.userInput = CalculatorData.CLEAR
        data.stack.clear()
        mainView.showChangesToUser(data.userInput, data.results)
    }

    override fun onDelButtonClick() {
        when (data.userInput.takeLast(1)) {
            ")" -> data.stack.addLast('(')
            "(" -> data.stack.pollLast()
        }

        data.userInput = CalculatorData.REMOVE_LAST_CHAR

        mainView.showChangesToUser(data.userInput, data.results)
    }

    override fun onEqualButtonClick() {
        if (Utils.splitTheInput(data.userInput).size > 2
            && !data.results.contentEquals(CalculatorData.ERRORMESSAGE)
        ) {
            data.equalButtonWasLastClicked = true
            mainView.swapColors()
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

        mainView.showChangesToUser(data.userInput, data.results)
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
            mainView.showChangesToUser(data.userInput, data.results)
        }
    }

    override fun restoreTextViewSizes(resultTextViewText: String) {
        if (data.equalButtonWasLastClicked) {
            data.equalButtonWasLastClicked = false

            data.userInput = CalculatorData.CLEAR
            data.userInput = resultTextViewText
            data.stack.clear()

            mainView.swapColors()
            mainView.showChangesToUser(data.userInput, data.results)
        }
    }
}