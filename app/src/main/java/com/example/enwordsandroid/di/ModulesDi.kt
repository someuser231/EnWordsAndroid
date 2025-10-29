package com.example.enwordsandroid.di

import com.example.data.local.WordsDb
import com.example.data.repo.WhRepo
import com.example.enwordsandroid.view_models.MainViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val mainModule = module {
    factory {
        WordsDb.getDb(get())
    }
    factoryOf(::WhRepo)
    viewModelOf(::MainViewModel)
}