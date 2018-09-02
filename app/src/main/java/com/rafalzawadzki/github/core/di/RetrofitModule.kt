package com.rafalzawadzki.github.core.di

import com.rafalzawadzki.github.core.data.endpoints.SearchApiEndpoints
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class RetrofitModule {

    @Provides
    @Singleton
    internal fun provideSearchApiEndpoints(retrofit: Retrofit): SearchApiEndpoints {
        return retrofit.create(SearchApiEndpoints::class.java)
    }
}
