package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.domain.models.WordModel

@Database(entities = [WordDbItem::class], version = 1)
@TypeConverters(value = [TypesConverter::class])
abstract class WordsDb: RoomDatabase() {
    companion object {
        fun getDb(context: Context): WordsDb =
            Room.databaseBuilder(
                context.applicationContext,
                WordsDb::class.java,
                "words.db"
            ).build()
    }
    abstract fun getWordDao(): WordDao

    fun toDbItem(model: WordModel): WordDbItem {
        return WordDbItem(
            word = model.word,
            tcUk = model.tcUk,
            tcUs = model.tcUs,
            wordForm = model.wordForm,
            tl = model.tl
        )
    }
}