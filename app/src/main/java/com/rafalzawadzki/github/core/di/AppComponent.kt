package com.rafalzawadzki.github.core.di

import com.rafalzawadzki.github.core.ThisApplication
import com.rafalzawadzki.github.search.repository.SearchRepositoryActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Injects methods have to be declared per class not the base so dagger 2 can generate the
 * providers graph
 */
@Singleton
@Component(modules = [(AndroidModule::class)])
interface AppComponent {

    fun inject(app: ThisApplication)

    fun inject(x: SearchRepositoryActivity)

}
