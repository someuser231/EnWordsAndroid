package com.example.domain.usecases

import com.example.domain.interfaces.WhRepoItf
import com.example.domain.models.WordModel
import kotlinx.coroutines.flow.Flow

class WhTranslate(val repo: WhRepoItf) {
    fun execute(word: String): Flow<WordModel> {
        return repo.translate(word)
    }
}