package com.gabmarquez.taskroom.viewmodel

import androidx.lifecycle.ViewModel
import com.gabmarquez.taskroom.utilities.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ListTaskViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListTaskViewModel::class)
    abstract fun bindListTaskViewModel(listTaskViewModel : ListTaskViewModel): ViewModel
}