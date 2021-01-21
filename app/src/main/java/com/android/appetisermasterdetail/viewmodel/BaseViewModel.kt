package com.android.appetisermasterdetail.viewmodel

import androidx.lifecycle.ViewModel
import com.android.appetisermasterdetail.di.component.DaggerViewModelComponent
import com.android.appetisermasterdetail.di.component.ViewModelComponent
import com.android.appetisermasterdetail.di.module.APIModule

abstract class BaseViewModel : ViewModel() {

    init {
        val injector: ViewModelComponent = DaggerViewModelComponent
            .builder()
            .apiModule(APIModule)
            .build()

        when (this) {
            is MainViewModel -> injector.inject(this)
        }

    }
}