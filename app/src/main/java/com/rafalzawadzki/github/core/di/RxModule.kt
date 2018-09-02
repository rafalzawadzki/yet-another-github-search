package com.rafalzawadzki.github.core.di

import com.rafalzawadzki.github.core.di.qualifiers.ForDomain
import com.rafalzawadzki.github.core.di.qualifiers.ForNetwork
import com.rafalzawadzki.github.core.di.qualifiers.ForUi

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class RxModule {

    @Provides
    @ForUi
    internal fun provideUiScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Provides
    @ForDomain
    internal fun provideDomainScheduler(): Scheduler {
        return Schedulers.newThread()
    }

    @Provides
    @ForNetwork
    internal fun provideNetworkScheduler(): Scheduler {
        return Schedulers.io()
    }
}
