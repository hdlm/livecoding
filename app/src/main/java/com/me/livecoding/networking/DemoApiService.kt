package com.me.livecoding.networking

import com.me.livecoding.networking.model.ResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface DemoApiService {
    @GET
    suspend fun getJokes(@Url url:String): Response<ResponseModel>

}