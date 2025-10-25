package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WordDbItem::class], version = 1)
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
}