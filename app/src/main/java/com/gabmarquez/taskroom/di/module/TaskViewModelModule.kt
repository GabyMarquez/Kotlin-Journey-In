package com.gabmarquez.taskroom.di.module

import androidx.lifecycle.ViewModel
import com.gabmarquez.taskroom.utilities.ViewModelKey
import com.gabmarquez.taskroom.viewmodel.DetailTaskViewModel
import com.gabmarquez.taskroom.viewmodel.ListTaskViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TaskViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListTaskViewModel::class)
    abstract fun bindListTaskViewModel(listTaskViewModel : ListTaskViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailTaskViewModel::class)
    abstract fun bindDetailTaskViewModel(detailTaskViewModel: DetailTaskViewModel): ViewModel
}