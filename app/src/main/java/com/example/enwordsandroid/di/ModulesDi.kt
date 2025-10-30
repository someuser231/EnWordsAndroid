package com.example.enwordsandroid.di

import com.example.data.local.MainDb
import com.example.data.repo.WordsRepo
import com.example.enwordsandroid.view_models.MainViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val mainModule = module {
    factory {
        MainDb.initDb(get())
    }
    factoryOf(::WordsRepo)
    viewModelOf(::MainViewModel)
}