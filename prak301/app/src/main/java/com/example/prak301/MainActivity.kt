package com.example.unscrambleapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.currentScrambledWord.observe(this, Observer {
            tvScrambledWord.text = it
        })

        viewModel.score.observe(this, Observer {
            tvScore.text = "SCORE: $it"
        })

        btnSubmit.setOnClickListener {
            val userGuess = etGuess.text.toString()
            viewModel.checkGuess(userGuess)
            etGuess.text.clear()
        }

        btnSkip.setOnClickListener {
            viewModel.getNextWord()
            etGuess.text.clear()
        }
    }
}
