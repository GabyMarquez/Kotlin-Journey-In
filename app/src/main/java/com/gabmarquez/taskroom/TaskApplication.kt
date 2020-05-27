package com.gabmarquez.taskroom

import com.gabmarquez.taskroom.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class TaskApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
       // return DaggerAppComponent.builder().application(this).build()
    }
}