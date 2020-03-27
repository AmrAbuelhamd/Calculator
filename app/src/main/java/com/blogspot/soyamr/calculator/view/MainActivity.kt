package com.blogspot.soyamr.calculator.view

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.soyamr.calculator.R
import com.blogspot.soyamr.calculator.presenter.Presenter
import com.blogspot.soyamr.calculator.presenter.PresenterMainActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), ViewParent {
    lateinit var presenterMainActivity: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenterMainActivity = PresenterMainActivity(this)
    }


    fun onButtonClick(view: View) {

        presenterMainActivity.restoreTextViewSizes(resultTextView.text.toString())

        view as Button
        when (view) {
            _0, _1, _2, _3, _4, _5, _6, _7, _8, _9 ->
                presenterMainActivity.OnNumButtonClick(view.text.toString())
            sum, sub, percent, mult, div -> presenterMainActivity.onOperatorButtonClick(view.text.toString())
            ac -> presenterMainActivity.onAcButtonClick()
            del -> presenterMainActivity.onDelButtonClick()
            equalButtonView -> presenterMainActivity.onEqualButtonClick()
            dot -> presenterMainActivity.onDotButtonClick(view.text.toString())
            brack -> presenterMainActivity.onBrackButtonClicked()
        }
    }

    override fun showChangesToUser(input: String, result: String) {
        userInputTextView.text = input
        resultTextView.text = result
    }

    override fun swapSize() {
        val temp = userInputTextView.textSize
        userInputTextView.setTextSize(TypedValue.COMPLEX_UNIT_FRACTION, resultTextView.textSize)
        resultTextView.setTextSize(TypedValue.COMPLEX_UNIT_FRACTION, temp)

        val temp1 = userInputTextView.textColors
        userInputTextView.setTextColor(resultTextView.textColors)
        resultTextView.setTextColor(temp1)
    }

}
