package com.rafalzawadzki.github.core.mvp.interfaces

import android.os.Bundle

interface Presenter<V: Any> {

    fun attachView(view: V)

    fun detachView()

    fun processIntent(extras: Bundle?)
}
