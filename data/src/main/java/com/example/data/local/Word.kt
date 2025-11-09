package com.example.data.local

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update

@Entity(tableName = "all_words")
data class WordDbItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "word")
    val word: String,

    @ColumnInfo(name = "tc_uk")
    val tcUk: String?,

    @ColumnInfo(name = "tc_us")
    val tcUs: String?,

    @ColumnInfo(name = "word_form")
    val wordForm: List<String>?,

    @ColumnInfo(name = "tl")
    val tl: List<String>?,

    @ColumnInfo(name = "learning_status")
    var learningStatus: Int,

    @ColumnInfo(name = "in_learning")
    var inLearning: Boolean = false,

    @ColumnInfo(name = "success_answer")
    var successAnswer: Int,

    @ColumnInfo(name = "fail_answer")
    var failAnswer: Int,
)

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWord(word: WordDbItem)

    @Query("select * from all_words")
    suspend fun getWords(): List<WordDbItem>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWord(word: WordDbItem)

    @Delete
    suspend fun deleteWord(word: WordDbItem)

    @Query("select * from all_words where in_learning = 1")
    suspend fun getLearnWords(): List<WordDbItem>

    @Query("select * from all_words where id = :id")
    suspend fun getItem(id: Int): WordDbItem
}
