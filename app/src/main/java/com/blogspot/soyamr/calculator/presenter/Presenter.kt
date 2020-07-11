package com.blogspot.soyamr.calculator.presenter

interface Presenter {

    fun onNumButtonClick(number: String)
    fun onOperatorButtonClick(operatorSign: String)
    fun onAcButtonClick()
    fun onEqualButtonClick()
    fun onBrackButtonClicked()
    fun onDotButtonClick(dotSign: String)
    fun restoreTextViewSizes(resultTextViewText: String)
    fun onDelButtonClick()

}