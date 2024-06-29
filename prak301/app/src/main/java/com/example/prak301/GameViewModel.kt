package com.example.unscrambleapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val _currentScrambledWord = MutableLiveData<String>()
    val currentScrambledWord: LiveData<String>
        get() = _currentScrambledWord

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val wordsList: MutableList<String> = mutableListOf(
        "flutter", "widget", "state", "context", "android", "ios", "dart", "stateful", "stateless"
    )
    private var currentWord = ""
    private var currentIndex = 0

    init {
        getNextWord()
        _score.value = 0
    }

    fun getNextWord() {
        if (currentIndex >= wordsList.size) {
            currentIndex = 0
        }
        currentWord = wordsList[currentIndex]
        _currentScrambledWord.value = currentWord.toCharArray().apply { shuffle() }.concatToString()
        currentIndex++
    }

    fun checkGuess(guess: String) {
        if (guess.equals(currentWord, ignoreCase = true)) {
            _score.value = (_score.value ?: 0) + 20
            getNextWord()
        }
    }
}
