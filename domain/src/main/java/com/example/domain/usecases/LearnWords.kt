package com.example.domain.usecases

import com.example.domain.interfaces.WordsRepoItf
import com.example.domain.models.WordModel

class LearnWords(val repo: WordsRepoItf, val word: WordModel?, val lnWords: List<WordModel>) {
    fun execute(know: Boolean): WordModel {
        if (word != null) {
            if (know) {
                word.successAnswer++
            }
            else {
                word.failAnswer++
            }
            word.learningStatus = learnStatus(word)
            UpdateWord(repo).execute(word)
        }
        return randWord()
    }

    private fun randWord(): WordModel {
        return lnWords.random()
    }

    private fun learnStatus(word: WordModel): Int {
        return when (word.successAnswer) {
            in 1..5 -> 1
            in 6..10 -> 2
            in 11..Int.MAX_VALUE -> 3
            else -> 0
        }
    }
}