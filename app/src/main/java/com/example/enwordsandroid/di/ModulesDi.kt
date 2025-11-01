package com.example.enwordsandroid.di

import com.example.data.local.MainDb
import com.example.data.repo.WordsRepo
import com.example.domain.interfaces.WordsRepoItf
import com.example.enwordsandroid.view_models.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    factory {
        MainDb.initDb(get())
    }
    factory<WordsRepoItf>{
        WordsRepo(get())
    }
    viewModel {
        MainViewModel(get())
    }
}