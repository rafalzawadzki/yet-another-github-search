package com.rafalzawadzki.github.core.data.services

import com.rafalzawadzki.github.core.data.ResultWrapper
import com.rafalzawadzki.github.core.data.models.Repository
import com.rafalzawadzki.github.core.data.providers.SearchProvider
import com.rafalzawadzki.github.core.di.qualifiers.ForDomain
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class SearchService
@Inject constructor(
        private val searchProvider: SearchProvider,
        @ForDomain private val domainScheduler: Scheduler) {

    fun searchRepositories(query: String): Single<ResultWrapper<Repository>> = searchProvider.searchRepositories(query)
}
