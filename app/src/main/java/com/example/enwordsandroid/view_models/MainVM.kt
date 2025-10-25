package com.example.enwordsandroid.view_models

import androidx.lifecycle.ViewModel
import com.example.data.repo.WhRepo
import com.example.domain.models.WordModel
import com.example.domain.usecases.WhTranslate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainVM: ViewModel() {

    var word = MutableStateFlow<WordModel?>(null)

    fun translate(wordInput: String) {
        CoroutineScope(Dispatchers.IO).launch {
            WhTranslate(WhRepo()).execute(wordInput).collect {
                word.value = it
            }
        }
    }
}