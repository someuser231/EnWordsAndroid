package com.example.domain.usecases

import com.example.domain.models.WordModel

class LearnWords(val words: List<WordModel>) {
    fun execute(know: Boolean): WordModel {
        return randWord()
    }

    private fun randWord(): WordModel {
        return words.random()
    }
}