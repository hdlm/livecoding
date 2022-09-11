package com.me.livecoding.networking

import com.me.livecoding.networking.model.ResponseModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DemoApiAdapter : ApiAdapter {
    override suspend fun selectJoke(): ResponseModel? {
        val url = "${BASE_URL}${PARAMS}"
        val call : Response<ResponseModel> = DemoApiAdapter.getRetrofit().create(DemoApiService::class.java).getJokes(url)
        if (!call.isSuccessful) {
            return null
        }
        return call.body()
    }

    companion object {
        private const val BASE_URL = "https://api.chucknorris.io/jokes/"
        private const val PARAMS = "random"

        fun getRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}