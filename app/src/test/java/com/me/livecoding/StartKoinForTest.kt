package com.me.livecoding

import com.me.livecoding.data.repository.JokesRepository
import com.me.livecoding.di.Modules.unitTestModule
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class StartKoinForTest : KoinComponent {

    @Before
    fun startKoinForTest() {
        if (GlobalContext.getOrNull() == null) {
            startKoin {
//                androidLogger()
                modules( unitTestModule )
                printLogger()
            }
        }
    }

    @Test
    fun consumoApiRest() {
        val repository by inject<JokesRepository>()
        repository.inquiryForJokes(1)
        val lst = repository.jokes
        assertTrue(lst.isNotEmpty())
        assertEquals(1, lst.size)
    }

    @After
    fun stopKoinAfterTest() = stopKoin()

}