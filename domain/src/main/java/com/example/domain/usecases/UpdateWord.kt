package com.example.domain.usecases

import com.example.domain.interfaces.WordsRepoItf
import com.example.domain.models.WordModel

class UpdateWord(val repo: WordsRepoItf) {
    fun execute(word: WordModel) {
        repo.updateItemDb(word)
    }
}