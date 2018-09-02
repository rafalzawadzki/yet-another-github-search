package com.rafalzawadzki.github.core.data.endpoints

import com.rafalzawadzki.github.core.data.ResultWrapper
import com.rafalzawadzki.github.core.data.models.Repository
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface SearchApiEndpoints {

    @GET("/search/repositories")
    fun searchRepositories(@Query("q") query: String): Single<ResultWrapper<Repository>>

}