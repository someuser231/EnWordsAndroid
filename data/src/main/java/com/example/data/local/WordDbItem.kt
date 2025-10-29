package com.example.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "all_words")
data class WordDbItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "word")
    val word: String,

    @ColumnInfo(name = "tc_uk")
    val tcUk: String?,

    @ColumnInfo(name = "tc_us")
    val tcUs: String?,

    @ColumnInfo(name = "word_form")
    val wordForm: List<String>?,

    @ColumnInfo(name = "tl")
    val tl: List<String>?
)
