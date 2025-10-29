package com.example.domain.usecases

import com.example.domain.interfaces.WhRepoItf
import com.example.domain.models.WordModel

class WhSaveWord(val repo: WhRepoItf) {
    fun execute(model: WordModel) {
        repo.insertToDb(model)
    }
}