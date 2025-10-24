package com.example.domain.models

data class WordModel(
    val word: String,
    val tcUk: String?,
    val tcUs: String?,
    val wordForm: List<String>?,
    val tl: List<String>?
)
