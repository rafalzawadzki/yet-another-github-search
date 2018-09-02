package com.rafalzawadzki.github.core.di

import com.rafalzawadzki.github.core.data.services.SearchService
import dagger.Lazy
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Services
@Inject constructor() {

    @Inject
    lateinit var searchService: Lazy<SearchService>

    fun searchService() = searchService.get()

}
