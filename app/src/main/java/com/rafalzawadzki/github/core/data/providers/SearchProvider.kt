package com.rafalzawadzki.github.core.data.providers

import com.rafalzawadzki.github.core.data.ResultWrapper
import com.rafalzawadzki.github.core.data.endpoints.SearchApiEndpoints
import com.rafalzawadzki.github.core.data.models.Repository
import com.rafalzawadzki.github.core.di.qualifiers.ForNetwork
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class SearchProvider
@Inject constructor(private val searchEndpoints: SearchApiEndpoints,
                    @ForNetwork private val scheduler: Scheduler) {

    fun searchRepositories(query: String): Single<ResultWrapper<Repository>> = searchEndpoints.searchRepositories(query)
            .subscribeOn(scheduler)

}
