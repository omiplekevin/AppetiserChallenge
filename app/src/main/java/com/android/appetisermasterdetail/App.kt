package com.android.appetisermasterdetail

import android.app.Application
import com.android.appetisermasterdetail.di.component.ApplicationComponent
import com.android.appetisermasterdetail.di.component.DaggerApplicationComponent
import com.android.appetisermasterdetail.di.module.ApplicationModule
import timber.log.Timber

class App : Application() {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    fun getApplicationComponent(): ApplicationComponent {
        return applicationComponent
    }
}