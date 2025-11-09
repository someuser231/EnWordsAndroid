package com.example.domain.usecases

import com.example.domain.interfaces.WordsRepoItf
import com.example.domain.models.WordModel
import kotlinx.coroutines.flow.Flow

class GetOneWord(val repo: WordsRepoItf) {
    fun execute(wordId: Int): Flow<WordModel> {
        return repo.getItemFromDb(wordId)
    }
}