package com.gabmarquez.taskroom.viewmodel

import androidx.lifecycle.ViewModel
import com.gabmarquez.taskroom.utilities.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailEditTaskViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailEditTaskViewModel::class)
    abstract fun bindDetailEditTaskViewModel(detailEditTaskViewModel: DetailEditTaskViewModel): ViewModel
}