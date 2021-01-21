package com.android.appetisermasterdetail.di.module

import android.app.Activity
import android.content.Context
import com.android.appetisermasterdetail.di.scope.ActivityContext
import dagger.Module
import dagger.Provides

@Module
class ActivityModule constructor(activity: Activity){

    private val act: Activity = activity

    @Provides
    @ActivityContext
    fun getContext(): Context {
        return this.act
    }

    @Provides
    fun getActivity(): Activity {
        return this.act
    }
}