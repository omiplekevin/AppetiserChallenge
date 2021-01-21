package com.android.appetisermasterdetail.di.module

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.android.appetisermasterdetail.di.scope.ApplicationContext
import com.android.appetisermasterdetail.helper.DatabaseHelper
import dagger.Module
import dagger.Provides


@Module
class ApplicationModule constructor(application: Application) {

    private val mApplication: Application = application

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return mApplication.applicationContext
    }

    @Provides
    fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    fun provideSharedPrefs(): SharedPreferences {
        return mApplication.getSharedPreferences("safetymeter-prefs", MODE_PRIVATE)
    }

    @Provides
    fun provideDatabaseHelper(): DatabaseHelper {
        return Room
            .databaseBuilder(
                mApplication.applicationContext,
                DatabaseHelper::class.java, DatabaseHelper.DATABASE_NAME
            )
            .build()
    }

}