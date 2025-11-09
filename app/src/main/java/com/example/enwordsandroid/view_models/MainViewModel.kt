package com.example.enwordsandroid.view_models

import androidx.lifecycle.ViewModel
import com.example.domain.interfaces.WordsRepoItf
import com.example.domain.models.WordModel
import com.example.domain.usecases.DeleteWord
import com.example.domain.usecases.GetInLearningWords
import com.example.domain.usecases.GetOneWord
import com.example.domain.usecases.GetWords
import com.example.domain.usecases.LearnWords
import com.example.domain.usecases.SaveWord
import com.example.domain.usecases.UpdateWord
import com.example.domain.usecases.WhTranslate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(val repo: WordsRepoItf): ViewModel() {
    var word = MutableStateFlow<WordModel?>(null)
    var dbWords = MutableStateFlow<MutableList<WordModel>?>(null)


    var lnWords = MutableStateFlow<MutableList<WordModel>?>(null)

    fun translate(wordInput: String) {
        CoroutineScope(Dispatchers.IO).launch {
            WhTranslate(repo).execute(wordInput).collect {
                word.value = it
            }
        }
    }
    fun saveWord(wordStr: String) {
        var curWord: WordModel? = null
        curWord =
            if (word.value != null) {
                word.value
            } else {
                WordModel(
                    word = wordStr,
                    tcUk = null,
                    tcUs = null,
                    wordForm = null,
                    tl = null,
                )
            }
        SaveWord(repo).execute(curWord!!)

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
    fun deleteWord(word: WordModel) {
        DeleteWord(repo).execute(word)
    }

    fun getLearnWords() {
        CoroutineScope(Dispatchers.IO).launch {
            GetInLearningWords(repo).execute().collect {
                lnWords.value = it
            }
        }
    }

    fun learnWords(curWord: WordModel?, know: Boolean): WordModel? {
        if (lnWords.value != null) {
            if (lnWords.value!!.isNotEmpty()) {
                return LearnWords(repo, curWord, lnWords.value!!).execute(know)
            }
        }
        return null
    }

    fun getOneWord(wordId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            GetOneWord(repo).execute(wordId).collect {
                word.value = it
            }
        }
    }
}