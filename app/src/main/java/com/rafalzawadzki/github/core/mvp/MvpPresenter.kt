package com.rafalzawadzki.github.core.mvp

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.VisibleForTesting
import com.rafalzawadzki.github.core.mvp.interfaces.Presenter
import com.rafalzawadzki.github.core.mvp.interfaces.View


open class MvpPresenter<V : View> : Presenter<V> {

    protected var view: V? = null

    @VisibleForTesting
    @CallSuper
    override fun attachView(view: V) {
        this.view = view
        onViewAttached()
    }

    @CallSuper
    override fun detachView() {
        view = null
        onViewDetached()
    }

    override fun processIntent(extras: Bundle?) {}

    protected open fun onViewAttached() {}

    protected open fun onViewDetached() {}
}
