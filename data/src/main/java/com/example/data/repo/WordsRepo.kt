package com.example.data.repo

import android.util.Log
import com.example.data.local.MainDb
import com.example.data.remote.RetrofitUtils
import com.example.data.remote.wh.WhApi
import com.example.data.remote.wh.WhWordDto
import com.example.domain.interfaces.WordsRepoItf
import com.example.domain.models.WordModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class WordsRepo(val db: MainDb): WordsRepoItf {
    override fun translate(word: String): Flow<WordModel> {
        val retrofit = RetrofitUtils.getInstance().create(WhApi::class.java)
        return flow {
            try {
                val response = retrofit.getWord(word)
                emit(responseToModel(response))
            }
            catch (e: Exception) {
                Log.e("EnWordsLog", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun insertToDb(word: WordModel) {
        CoroutineScope(Dispatchers.IO).launch {
            db.getWordDao().insertWord(db.wordToDbItem(word))
        }
    }

    override fun loadFromDb(): Flow<MutableList<WordModel>> {
        return flow {
            try {
                val dbItems = db.getWordDao().getWords()
                val models = mutableListOf<WordModel>()
                for (i in dbItems) {
                    models.add(db.wordToModel(i))
                }
                emit(models)
            }
            catch (e: Exception) {
                Log.e("EnWordsLog", e.toString())
            }
        }
    }

    override fun updateItemDb(word: WordModel) {
        CoroutineScope(Dispatchers.IO).launch {
            db.getWordDao().updateWord(db.wordToDbItem(word))
        }

    }

    override fun deleteItemDb(word: WordModel) {
        CoroutineScope(Dispatchers.IO).launch {
            db.getWordDao().deleteWord(db.wordToDbItem(word))
        }
    }

    override fun getLearnWords(): Flow<MutableList<WordModel>> {
        return flow {
            try {
                val dbItems = db.getWordDao().getLearnWords()
                val models = mutableListOf<WordModel>()
                for (i in dbItems) {
                    models.add(db.wordToModel(i))
                }
                emit(models)
            }
            catch (e: Exception) {
                Log.e("EnWordsLog", e.toString())
            }
        }
    }

    private fun responseToModel(resp: WhWordDto): WordModel {
        return WordModel(
            word = resp.word,
            tcUk = resp.tc_uk,
            tcUs = resp.tc_us,
            wordForm = resp.word_form,
            tl = resp.tl
        )
    }
}