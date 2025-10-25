package com.example.data.repo

import android.util.Log
import com.example.data.remote.RetrofitUtils
import com.example.data.remote.wh.WhApi
import com.example.data.remote.wh.WhResponse
import com.example.domain.interfaces.WhRepoItf
import com.example.domain.models.WordModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WhRepo: WhRepoItf {
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
        TODO("Not yet implemented")
    }

    override fun loadFromDb() {
        TODO("Not yet implemented")
    }

    private fun responseToModel(resp: WhResponse): WordModel {
        return WordModel(
            word = resp.word,
            tcUk = resp.tc_uk,
            tcUs = resp.tc_us,
            wordForm = resp.word_form,
            tl = resp.tl
        )
    }
}