package com.example.enwordsandroid.view_models

import androidx.lifecycle.ViewModel
import com.example.data.repo.WordsRepo
import com.example.domain.models.WordModel
import com.example.domain.usecases.GetWords
import com.example.domain.usecases.SaveWord
import com.example.domain.usecases.UpdateWord
import com.example.domain.usecases.WhTranslate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(val repo: WordsRepo): ViewModel() {
    var word = MutableStateFlow<WordModel?>(null)
    var dbWords = MutableStateFlow<MutableList<WordModel>?>(null)

    fun translate(wordInput: String) {
        CoroutineScope(Dispatchers.IO).launch {
            WhTranslate(repo).execute(wordInput).collect {
                word.value = it
            }
        }
    }
    fun saveWord() {
        if (word.value != null) {
            SaveWord(repo).execute(word.value!!)
        }
    }
    fun getWords() {
        CoroutineScope(Dispatchers.IO).launch {
            GetWords(repo).execute().collect {
                dbWords.value = it
            }
        }
    }
    fun updateWord(word: WordModel) {
        UpdateWord(repo).execute(word)
    }
}