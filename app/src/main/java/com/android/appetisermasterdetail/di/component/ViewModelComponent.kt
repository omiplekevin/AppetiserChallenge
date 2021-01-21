package com.android.appetisermasterdetail.di.component

import com.android.appetisermasterdetail.di.module.APIModule
import com.android.appetisermasterdetail.viewmodel.MainViewModel
import dagger.Component

@Component(modules = [APIModule::class])
interface ViewModelComponent {

    fun inject(mainViewModel: MainViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelComponent

        fun apiModule(apiModule: APIModule): Builder
    }

}