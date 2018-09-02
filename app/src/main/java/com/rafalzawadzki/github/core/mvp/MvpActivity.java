package com.rafalzawadzki.github.core.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import com.rafalzawadzki.github.core.BaseActivity;
import com.rafalzawadzki.github.core.mvp.interfaces.Presenter;
import com.rafalzawadzki.github.core.mvp.interfaces.View;

import javax.inject.Inject;

public abstract class MvpActivity<T extends Presenter> extends BaseActivity implements View {

    @Inject
    protected T presenter;

    private boolean attachedOnResult = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.processIntent(getIntent().getExtras());
    }

    @CallSuper
    @SuppressWarnings("unchecked")
    @Override
    protected void onStart() {
        super.onStart();
        if (!attachedOnResult) {
            presenter.attachView(this);
        }
    }

    //https://stackoverflow.com/questions/3354955/onactivityresult-called-prematurely
    //onActivityResult is called before any activity creation lifecycle events
    @CallSuper
    @SuppressWarnings("unchecked")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.attachView(this);
        attachedOnResult = true;
    }

    @CallSuper
    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.detachView();
            attachedOnResult = false;
        }
    }
}
