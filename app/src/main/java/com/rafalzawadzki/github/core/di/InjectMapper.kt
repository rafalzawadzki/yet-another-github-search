package com.rafalzawadzki.github.core.di

import com.rafalzawadzki.github.core.BaseActivity
import com.rafalzawadzki.github.core.ThisApplication
import com.rafalzawadzki.github.search.repository.SearchRepositoryActivity

object InjectMapper {

    fun inject(clazz: BaseActivity) {
        val activityGraph = (clazz.applicationContext as ThisApplication).applicationComponent

        when (clazz) {
            is SearchRepositoryActivity -> activityGraph.inject(clazz)
            else -> throw RuntimeException("Dependency Injection: Activity requested injection, but not found: " + clazz.javaClass.name)
        }
    }

}
