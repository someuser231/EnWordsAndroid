package com.example.domain.usecases

import com.example.domain.interfaces.WordsRepoItf
import com.example.domain.models.WordModel
import kotlinx.coroutines.flow.Flow

class GetWords(val repo: WordsRepoItf) {
    fun execute(): Flow<MutableList<WordModel>> {
        return repo.loadFromDb()
    }
}