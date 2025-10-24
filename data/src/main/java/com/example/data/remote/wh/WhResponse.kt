package com.example.data.remote.wh

data class WhResponse(
    val word: String,
    val tc_uk: String,
    val tc_us: String,
    val word_form: List<String>,
    val tl: List<String>
)
