package com.gabmarquez.taskroom.di.module

import com.gabmarquez.taskroom.view.detail.DetailAddTask
import com.gabmarquez.taskroom.view.detail.DetailEditTask
import com.gabmarquez.taskroom.view.list.ListTask
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ApplicationModule {

    @ContributesAndroidInjector
    abstract fun contributeListTask(): ListTask

    @ContributesAndroidInjector
    abstract fun contributeDetailAddTask(): DetailAddTask

    @ContributesAndroidInjector
    abstract fun contributeDetailEditTask(): DetailEditTask
}