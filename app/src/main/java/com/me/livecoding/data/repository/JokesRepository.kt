package com.me.livecoding.data.repository

import com.me.livecoding.networking.model.ResponseModel

interface JokesRepository {
    val jokes: List<ResponseModel>

    /**
     * El método es responsable de armar una lista con [n] bromas
     */
    fun inquiryForJokes(quantity: Int)
}