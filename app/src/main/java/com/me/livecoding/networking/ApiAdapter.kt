package com.me.livecoding.networking

import com.me.livecoding.networking.model.ResponseModel

interface ApiAdapter {
    /**
     * El método es responsable de solicitar 20 jokes
     */
    suspend fun selectJoke(): ResponseModel?
}