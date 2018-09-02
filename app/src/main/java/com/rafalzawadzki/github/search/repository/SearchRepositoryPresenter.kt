package com.rafalzawadzki.github.search.repository

import com.rafalzawadzki.github.R
import com.rafalzawadzki.github.core.data.ResultWrapper
import com.rafalzawadzki.github.core.data.models.Repository
import com.rafalzawadzki.github.core.di.Services
import com.rafalzawadzki.github.core.di.qualifiers.ForUi
import com.rafalzawadzki.github.core.mvp.MvpPresenter
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SearchRepositoryPresenter
@Inject constructor(services: Services, @ForUi private val uiScheduler: Scheduler)
    : MvpPresenter<SearchRepositoryActivity>() {

    private var searchService = services.searchService()
    private val disposables = CompositeDisposable()
    private val searchSuggestions = listOf("bitcoin", "react native")

    override fun onViewAttached() {
        view?.setSearchSuggestions(searchSuggestions)
    }

    override fun onViewDetached() {
        disposables.clear()
    }

    fun search(query: String) {
        disposables.add(searchService.searchRepositories(query)
                .observeOn(uiScheduler)
                .doOnSubscribe {
                    view?.showLoading(true)
                    view?.showEmptyState(false)
                }
                .doAfterTerminate { view?.showLoading(false) }
                .subscribe(this::loadSearchResultsSuccess , this::loadSearchResultsError))
    }

    fun loadSearchResultsSuccess(result: ResultWrapper<Repository>) {
        view?.setItems(result.items)
        view?.showEmptyState(result.totalCount == 0)
    }

    fun loadSearchResultsError(throwable: Throwable) {
        throwable.printStackTrace()
        view?.showError(R.string.error_generic)
    }

    fun onSearchSuggestionClicked(text: String) {
        search(text)
    }

    fun onRepositoryClicked(repository: Repository) {
        view?.openBrowser(repository.htmlUrl)
    }
}