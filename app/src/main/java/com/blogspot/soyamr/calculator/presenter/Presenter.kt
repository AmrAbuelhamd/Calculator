package com.blogspot.soyamr.calculator.presenter

import android.view.View
import android.widget.Button

interface Presenter {

    fun OnNumButtonClick(number: String)
    fun onOperatorButtonClick(operatorSign: String)
    fun onAcButtonClick()
    fun onEqualButtonClick()
    fun onBrackButtonClicked()
    fun onDotButtonClick(dotSign: String)
    fun restoreTextViewSizes(resultTextViewText: String)
    fun onDelButtonClick()

}