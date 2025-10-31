package com.example.domain.models

data class WordModel(
    val id: Int? = null,
    val word: String,
    val tcUk: String?,
    val tcUs: String?,
    val wordForm: List<String>?,
    val tl: List<String>?,
    var learningStatus: Int = 0,
    var inLearning: Boolean = false,
    var successAnswer: Int = 0,
    var failAnswer: Int = 0,
)
