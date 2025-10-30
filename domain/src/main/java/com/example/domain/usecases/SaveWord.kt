package com.example.domain.usecases

import com.example.domain.interfaces.WordsRepoItf
import com.example.domain.models.WordModel

class SaveWord(val repo: WordsRepoItf) {
    fun execute(model: WordModel) {
        repo.insertToDb(model)
    }
}