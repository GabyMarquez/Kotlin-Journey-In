package com.gabmarquez.taskroom.di.module

import com.gabmarquez.taskroom.MainActivity
import com.gabmarquez.taskroom.viewmodel.ListTaskViewModel
import com.gabmarquez.taskroom.viewmodel.ListTaskViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class  ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [ListTaskViewModelModule::class, ApplicationModule::class])
    abstract fun contributeMainActivity(): MainActivity

}