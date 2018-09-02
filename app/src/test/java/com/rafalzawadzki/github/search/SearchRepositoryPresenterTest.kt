package com.rafalzawadzki.github.search

import com.rafalzawadzki.github.core.data.ResultWrapper
import com.rafalzawadzki.github.core.data.models.Repository
import com.rafalzawadzki.github.core.data.services.SearchService
import com.rafalzawadzki.github.core.di.Services
import com.rafalzawadzki.github.newRepositoriesResultOfSize
import com.rafalzawadzki.github.search.repository.SearchRepositoryActivity
import com.rafalzawadzki.github.search.repository.SearchRepositoryPresenter
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.*
import org.mockito.InjectMocks

class SearchRepositoryPresenterTest {

    val searchService : SearchService = mock()
    val services : Services = mock()
    val view: SearchRepositoryActivity = mock()

    @InjectMocks
    var presenter: SearchRepositoryPresenter? = null

    @Before
    fun setup() {
        whenever(services.searchService()).thenReturn(searchService)
        presenter = SearchRepositoryPresenter(services, Schedulers.trampoline())
        presenter?.attachView(view)
    }

    @Test
    fun search_success_withResults() {
        val repositories = newRepositoriesResultOfSize(10)
        whenever(searchService.searchRepositories("query")).thenReturn(Single.just(repositories))

        presenter?.search("query")

        verify(searchService).searchRepositories("query")
        verify(view).showLoading(true)
        verify(view).showLoading(false)
        verify(view, never()).showError(anyInt())
        verify(view, never()).showError(anyString())
        verify(view).setItems(repositories.items)
        verify(view, times(2)).showEmptyState(eq(false))
        verify(view, never()).showEmptyState(eq(true))
    }

    @Test
    fun search_success_empty() {
        val repositories = ResultWrapper<Repository>(listOf())
        whenever(searchService.searchRepositories("query")).thenReturn(Single.just(repositories))

        presenter?.search("query")

        verify(searchService).searchRepositories("query")
        verify(view).showLoading(true)
        verify(view).showEmptyState(false)
        verify(view).showLoading(false)
        verify(view, never()).showError(anyInt())
        verify(view, never()).showError(anyString())
        verify(view).setItems(repositories.items)
        verify(view).showEmptyState(true)
    }

    @Test
    fun search_success_error() {
        whenever(searchService.searchRepositories("query")).thenReturn(Single.error(Throwable("some error")))

        presenter?.search("query")

        verify(searchService).searchRepositories("query")
        verify(view).showLoading(true)
        verify(view).showEmptyState(false)
        verify(view).showLoading(false)
        verify(view).showError(anyInt())
        verify(view, never()).setItems(anyList())
        verify(view, never()).showEmptyState(true)
    }

}
