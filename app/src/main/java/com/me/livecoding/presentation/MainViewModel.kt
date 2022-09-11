package com.me.livecoding.presentation

import androidx.lifecycle.ViewModel
import com.me.livecoding.data.repository.JokesRepositoryImpl
import com.me.livecoding.networking.model.ResponseModel

class MainViewModel(
    private val repository: JokesRepositoryImpl
) : ViewModel() {

    val jokes: List<ResponseModel>
        get() = repository.jokes

    /**
     * Data-binding [MainViewModel]ViewModel - []
     */
    var onJokesUpdated: ( (List<ResponseModel>) -> Unit )? = null
        set(value) {
            field = value
            onJokesUpdated?.invoke(jokes)
        }

    /**
     * El método es responsable de consultar 20 jokes
     */
    fun retrieveJokes(quantity: Int = 20) {
        repository.inquiryForJokes(quantity)
        onJokesUpdated?.invoke(jokes)
    }
}