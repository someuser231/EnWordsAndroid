package com.example.domain.interfaces

import com.example.domain.models.WordModel
import kotlinx.coroutines.flow.Flow

interface WhRepoItf {
    fun translate(word: String): Flow<WordModel>

    fun insertToDb()
    fun loadFromDb()

}