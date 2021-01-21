package com.android.appetisermasterdetail.di.component

import com.android.appetisermasterdetail.activity.MainActivity
import com.android.appetisermasterdetail.di.module.APIModule
import com.android.appetisermasterdetail.di.module.ActivityModule
import com.android.appetisermasterdetail.di.scope.PerActivity
import dagger.Component

@PerActivity
@Component(dependencies = [(ApplicationComponent::class)], modules = [ActivityModule::class, APIModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {

        fun build(): ActivityComponent

        fun activityModule(activityModule: ActivityModule): Builder

        fun applicationComponent(applicationComponent: ApplicationComponent): Builder
    }

}