package com.android.appetisermasterdetail.di.component

import android.app.Application
import android.content.Context
import com.android.appetisermasterdetail.App
import com.android.appetisermasterdetail.di.module.ApplicationModule
import com.android.appetisermasterdetail.di.scope.ApplicationContext
import com.android.appetisermasterdetail.helper.DatabaseHelper
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {

    fun inject(app: App)

    @ApplicationContext
    fun getContext(): Context

    fun getApplication(): Application

    fun getDatabaseHelper(): DatabaseHelper

    @Component.Builder
    interface Builder {

        fun build(): ApplicationComponent

        fun applicationModule(applicationModule: ApplicationModule): Builder

    }
}