package com.me.livecoding.di

import com.me.livecoding.data.repository.JokesRepository
import com.me.livecoding.data.repository.JokesRepositoryImpl
import com.me.livecoding.presentation.MainViewModel
import org.koin.dsl.module

object Modules {
    val appModule = module {
        single { JokesRepositoryImpl() }
        single { MainViewModel(get()) }
    }

    val unitTestModule = module {
        factory<JokesRepository> { JokesRepositoryImpl() }
    }
}