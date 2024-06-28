package com.example.prak201

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val costOfServiceEditText: EditText = findViewById(R.id.costOfService)
        val tipOptions: RadioGroup = findViewById(R.id.tipOptions)
        val roundUpSwitch: Switch = findViewById(R.id.roundUpSwitch)
        val calculateButton: Button = findViewById(R.id.calculateButton)
        val tipResultTextView: TextView = findViewById(R.id.tipResult)

        calculateButton.setOnClickListener {
            val costString = costOfServiceEditText.text.toString()
            val cost = costString.toDoubleOrNull()
            if (cost == null || cost == 0.0) {
                tipResultTextView.text = "Tip Amount: $0.00"
                return@setOnClickListener
            }

            val tipPercentage = when (tipOptions.checkedRadioButtonId) {
                R.id.optionTwentyPercent -> 0.20
                R.id.optionEighteenPercent -> 0.18
                else -> 0.15
            }

            var tip = cost * tipPercentage
            if (roundUpSwitch.isChecked) {
                tip = ceil(tip)
            }

            val formattedTip = java.text.NumberFormat.getCurrencyInstance().format(tip)
            tipResultTextView.text = "Tip Amount: $formattedTip"
        }
    }
}
