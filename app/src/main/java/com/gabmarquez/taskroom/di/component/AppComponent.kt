package com.gabmarquez.taskroom.di.component

import android.app.Application
import com.gabmarquez.taskroom.base.TaskApplication
import com.gabmarquez.taskroom.di.module.ActivityBuildersModule
import com.gabmarquez.taskroom.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuildersModule::class])

interface AppComponent : AndroidInjector<TaskApplication> {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}