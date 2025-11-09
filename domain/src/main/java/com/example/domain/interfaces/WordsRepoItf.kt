package com.example.domain.interfaces

import com.example.domain.models.WordModel
import kotlinx.coroutines.flow.Flow

interface WordsRepoItf {
    fun translate(word: String): Flow<WordModel>

    fun insertToDb(word: WordModel)
    fun loadFromDb(): Flow<MutableList<WordModel>>
    fun updateItemDb(word: WordModel)
    fun deleteItemDb(word: WordModel)
    fun getLearnWords(): Flow<MutableList<WordModel>>
    fun getItemFromDb(id: Int): Flow<WordModel>
}