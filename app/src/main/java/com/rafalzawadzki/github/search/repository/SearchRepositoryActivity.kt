package com.rafalzawadzki.github.search.repository

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import com.rafalzawadzki.github.R
import com.rafalzawadzki.github.core.data.models.Repository
import com.rafalzawadzki.github.core.misc.show
import com.rafalzawadzki.github.core.mvp.MvpActivity
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.include_search_repository_empty.*
import kotlinx.android.synthetic.main.include_toolbar.*
import javax.inject.Inject

class SearchRepositoryActivity: MvpActivity<SearchRepositoryPresenter>() {

    @Inject
    lateinit var repositoryAdapter: RepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setUI()
    }

    private fun setUI() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.search_repository_title)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = repositoryAdapter
        list.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        repositoryAdapter.clickListener = presenter::onRepositoryClicked
        containerSwipeRefresh.setColorSchemeResources(R.color.colorPrimary)
    }

    fun setItems(repositories: List<Repository>) {
        repositoryAdapter.setItems(repositories)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.isFocusable = false
        searchView.maxWidth = Int.MAX_VALUE
        searchView.queryHint = getString(R.string.search_input_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                presenter.search(s)
                return false
            }

            override fun onQueryTextChange(s: String) = false
        })
        return true
    }

    fun showLoading(show: Boolean) {
        containerSwipeRefresh.isEnabled = show
        containerSwipeRefresh.isRefreshing = show
    }

    fun showEmptyState(show: Boolean) {
        containerEmptyState.show(show)
        txtEmptyTitle.show(show)
    }

    fun setSearchSuggestions(searchSuggestions: List<String>) {
        btnSuggestion1.text = searchSuggestions[0]
        btnSuggestion2.text = searchSuggestions[1]
        btnSuggestion1.setOnClickListener { presenter.onSearchSuggestionClicked(searchSuggestions[0]) }
        btnSuggestion2.setOnClickListener { presenter.onSearchSuggestionClicked(searchSuggestions[1]) }
    }

    fun openBrowser(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(url) })
    }

}