/*
 * https://studyviewer.com/android-calculator-in-kotlin-using-android-studio/
 * https://developer.android.com/studio/run
 */
package com.personalized.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {

    //lateinit var outputTextView: TextView
    var lastNumaric: Boolean= false
    var stateError: Boolean = false
    var lastDot :Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View)
    {
        val textView: TextView = findViewById(R.id.textView2) as TextView
        if(stateError)
        {
            textView.text=(view as Button).text
            stateError=false
        }else {
            textView.append((view as Button).text)
        }
        lastNumaric=true
    }

    fun onDecimalPoint(view: View)
    {
        if(lastNumaric && !stateError && !lastDot)
        {
            val textView: TextView = findViewById(R.id.textView2) as TextView
            textView.append(".")
            lastNumaric=false
            lastDot=true
        }
    }

    fun onOperator (view: View)
    {
        if(lastNumaric && !stateError)
        {
            val textView: TextView = findViewById(R.id.textView2) as TextView
            textView.append((view as Button).text)
            lastNumaric=false
            lastDot=false
        }
    }


    fun onClear(view: View)
    {
        val textView: TextView = findViewById(R.id.textView2) as TextView
        textView.text = ""
        lastNumaric=false
        stateError=false
        lastDot=false
    }
    fun onEqual(view: View)
    {
        val textView: TextView = findViewById(R.id.textView2) as TextView
        if(lastNumaric && !stateError)
        {
            val text = textView.text.toString()
            val expression= ExpressionBuilder(text).build()
            try
            {
                val result= expression.evaluate()
                textView.text= result.toString()
                lastDot=true
            }catch (ex:Exception)
            {
                textView.text="Error"
                stateError=true
                lastNumaric=false
            }
        }

    }


}