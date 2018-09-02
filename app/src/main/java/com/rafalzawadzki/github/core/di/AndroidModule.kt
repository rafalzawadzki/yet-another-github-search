package com.rafalzawadzki.github.core.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.rafalzawadzki.github.core.ThisApplication
import com.rafalzawadzki.github.core.data.RetrofitHelper
import com.rafalzawadzki.github.core.di.qualifiers.ForApplication
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * AndroidModule provides singletons available with the Application object.
 */
@Module(includes = [(RetrofitModule::class), (RxModule::class)])
class AndroidModule(private val application: Application) {

    /**
     * Provides the Application context.
     * Consumers must annotate their injectables with @ForApplication to differentiate
     * it from an Activity's context.
     */
    @Provides
    @Singleton
    @ForApplication
    internal fun provideApplicationContext(): Context {
        return application
    }

    /**
     * Provides the Application Singleton.
     */
    @Provides
    @Singleton
    @ForApplication
    internal fun provideApplication(): ThisApplication {
        return application as ThisApplication
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(): Retrofit {
        return RetrofitHelper.buildApiClient().build()
    }

    @Provides
    @Singleton
    fun provideResources(): Resources {
        return application.resources
    }
}

