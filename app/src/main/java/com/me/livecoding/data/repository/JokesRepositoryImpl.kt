package com.me.livecoding.data.repository

import com.me.livecoding.networking.DemoApiAdapter
import com.me.livecoding.networking.model.ResponseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

open class JokesRepositoryImpl : JokesRepository {
    private val coroutineJob = Job()
    private val coroutineScope = CoroutineScope( ( Dispatchers.IO + coroutineJob))

    protected val _jokes : MutableList<ResponseModel> = mutableListOf()

    private val apiAdapter: DemoApiAdapter = DemoApiAdapter()

    override val jokes: List<ResponseModel>
        get() = _jokes


    /**
     * El método es responsable de armar una lista con [n] bromas
     */
    override fun inquiryForJokes(quantity: Int) {
        coroutineScope.launch {
            for(i in 1..quantity) {
                val flowOfJokes = getJokeSync()
                flowOfJokes.collect { value ->
                    value?.let {
                        _jokes.add(it)
                    }
                }
            }
        }
    }

    /**
     * El método se encarga de cargar los Jokes desde el API RESTFul
     */
    protected open fun getJokeSync() : Flow<ResponseModel?> = run {
        flow {
            val response: ResponseModel? = apiAdapter.selectJoke()
            emit(response)
        }
    }
}