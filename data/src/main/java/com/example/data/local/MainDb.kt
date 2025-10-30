package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.domain.models.LearningStatusModel
import com.example.domain.models.WordModel
import com.example.domain.utils.LearningStatusUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        WordDbItem::class
               ],
    version = 1
)
@TypeConverters(value = [TypesConverter::class])
abstract class MainDb: RoomDatabase() {
    companion object {
        fun initDb(context: Context): MainDb {
            val db = Room.databaseBuilder(
                context.applicationContext,
                MainDb::class.java,
                "EnWords.db"
            ).build()
            return db
        }
    }
    abstract fun getWordDao(): WordDao

    fun wordToDbItem(model: WordModel): WordDbItem {
        return WordDbItem(
            id = model.id ?: 0,
            word = model.word,
            tcUk = model.tcUk,
            tcUs = model.tcUs,
            wordForm = model.wordForm,
            tl = model.tl,
            learningStatus = model.learningStatus,
            inLearning = model.inLearning
        )
    }
    fun wordToModel(item: WordDbItem): WordModel {
        return WordModel(
            id = item.id,
            word = item.word,
            tcUk = item.tcUk,
            tcUs = item.tcUs,
            wordForm = item.wordForm,
            tl = item.tl,
            learningStatus = item.learningStatus,
            inLearning = item.inLearning
        )
    }
}