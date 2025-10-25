package com.example.domain.models

data class WordModel(
    val id: Int? = null,
    val word: String,
    val tcUk: String?,
    val tcUs: String?,
    val wordForm: List<String>?,
    val tl: List<String>?
)
