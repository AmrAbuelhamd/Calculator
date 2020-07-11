package com.blogspot.soyamr.calculator.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.soyamr.calculator.R
import com.blogspot.soyamr.calculator.presenter.MainPresenter
import com.blogspot.soyamr.calculator.presenter.Presenter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), ViewParent {
    lateinit var MainPresenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainPresenter = MainPresenter(this)

//        _0.setOnClickListener { v->onButton(v) }
    }

//    fun onButton(v: View) {
//
//    }

    fun onButtonClick(view: View) {

        MainPresenter.restoreTextViewSizes(resultTextView.text.toString())

        view as Button
        when (view) {
            _0, _1, _2, _3, _4, _5, _6, _7, _8, _9 ->
                MainPresenter.onNumButtonClick(view.text.toString())
            sum, sub, percent, mult, div -> MainPresenter.onOperatorButtonClick(view.text.toString())
            ac -> MainPresenter.onAcButtonClick()
            del -> MainPresenter.onDelButtonClick()
            equalButtonView -> MainPresenter.onEqualButtonClick()
            dot -> MainPresenter.onDotButtonClick(view.text.toString())
            brack -> MainPresenter.onBrackButtonClicked()
        }
    }

    override fun showChangesToUser(input: String, result: String) {
        userInputTextView.text = input
        resultTextView.text = result
    }

    override fun swapColors() {
        val temp = userInputTextView.textColors
        userInputTextView.setTextColor(resultTextView.textColors)
        resultTextView.setTextColor(temp)
    }

}
