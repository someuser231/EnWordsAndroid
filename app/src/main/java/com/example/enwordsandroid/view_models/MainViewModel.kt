package com.example.enwordsandroid.view_models

import androidx.lifecycle.ViewModel
import com.example.data.repo.WhRepo
import com.example.domain.models.WordModel
import com.example.domain.usecases.WhSaveWord
import com.example.domain.usecases.WhTranslate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(val repo: WhRepo): ViewModel() {
    var word = MutableStateFlow<WordModel?>(null)

    fun translate(wordInput: String) {
        CoroutineScope(Dispatchers.IO).launch {
            WhTranslate(repo).execute(wordInput).collect {
                word.value = it
            }
        }
    }

    fun saveWord() {
        if (word.value != null) {
            WhSaveWord(repo).execute(word.value!!)
        }
    }
}