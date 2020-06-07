package com.gabmarquez.taskroom.di.module

import com.gabmarquez.taskroom.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [TaskViewModelModule::class, ApplicationModule::class])
    abstract fun contributeMainActivity(): MainActivity
}