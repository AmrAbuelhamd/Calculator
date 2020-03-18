package com.blogspot.soyamr.calculator

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var userInputText = ""
    var results: Double = 0.0
    var resultButtonWasLastClicked = false

    //    var openBrack = 0
//    var closeBrack = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        userInputTextView.isSelected = true
    }

    fun OnNumButtonClick(view: View) {
        if (resultButtonWasLastClicked) {
            afteEqualClicked()
        }
        val button = view as Button
        userInputText = userInputText.plus(button.text)
        userInputTextView.text = userInputText

        displayResults()
    }

    fun OnOperatorButtonClick(view: View) {
        if (resultButtonWasLastClicked) {
            afteEqualClicked()
        }
        val button = view as Button
        if (userInputText.isEmpty() || userInputText.endsWith(button.text))
            return

        if (Utils.isOperator(userInputText.last()))
            userInputText = userInputText.dropLast(1)

        userInputText = userInputText.plus(button.text)
        userInputTextView.text = userInputText
    }

    fun onDeleteButtonClick(view: View) {

        when (view) {
            del -> {
                if (resultButtonWasLastClicked) {
                    afteEqualClicked()
                    return
                }
                userInputText = userInputText.dropLast(1)
                userInputTextView.text = userInputText
                displayResults()
            }
            ac -> {
                if (resultButtonWasLastClicked) {
                    afteEqualClicked()
                    return
                }
                userInputText = ""
                userInputTextView.text = userInputText
                resultTextView.text = userInputText
            }
        }
    }

    fun onEqualButtonClick(view: View) {
        if (userInputText.isNotEmpty() && Utils.splitTheInput(userInputText).size > 2) {
            resultButtonWasLastClicked = true
            Utils.swapSize(resultTextView, userInputTextView)
        }
    }

    fun displayResults() {
        if (userInputText.isNotEmpty() && Utils.splitTheInput(userInputText).size > 2) {
            results =
                Infix.inf2postF(Utils.removeTheLastOperatorIfExists(userInputText))

//            if (results % 1 == 0.0) results.toString().dropLast(2)
            resultTextView.text =
                if (results % 1 == 0.0) results.toString().dropLast(2) else results.toString()
        } else
            resultTextView.text = ""
    }

    fun onBrackButtonClicked(view: View) {
//        if (Utils.isOperator(userInputText.last())) {
//            userInputText = userInputText.plus("(")
//            openBrack++
//            userInputTextView.text = userInputText
//        } else if (!Utils.isOperator(userInputText.last())
//            && userInputText.last() != '(' && openBrack - closeBrack > 0
//        ) {
//            userInputText = userInputText.plus(")")
//            closeBrack--
//            userInputTextView.text = userInputText
//        }
        Toast.makeText(this, "under development", Toast.LENGTH_SHORT).show()
    }

    fun onDotButtonClick(view: View) {
        if (resultButtonWasLastClicked) {
            afteEqualClicked()
        }
        if (userInputText.isNotEmpty() && userInputText.last().isDigit()) {
            userInputText = userInputText.plus(".")
            userInputTextView.text = userInputText
        }
    }

    fun afteEqualClicked() {
        resultButtonWasLastClicked = false

        userInputTextView.textSize = 90f
        resultTextView.textSize = 30f
        userInputTextView.setTextColor(Color.parseColor("#000000"))
        resultTextView.setTextColor(Color.parseColor("#A9A9A9"))

        userInputText = resultTextView.text.toString()
        userInputTextView.text = userInputText
        resultTextView.text = ""
    }

}
