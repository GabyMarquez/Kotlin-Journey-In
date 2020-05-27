package com.gabmarquez.taskroom.di.module

import android.app.Application
import androidx.room.Room
import com.gabmarquez.taskroom.repository.TaskRepositoryModule
import com.gabmarquez.taskroom.repository.local.TaskDao
import com.gabmarquez.taskroom.repository.local.TaskDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun providesAppDatabase(application: Application) : TaskDatabase{
        return Room.databaseBuilder(application, TaskDatabase::class.java,"task_room_db").build()
    }

    @Singleton
    @Provides
    fun providesTaskDao(taskDatabase: TaskDatabase): TaskDao {
        return taskDatabase.taskDao()
    }

    @Provides
    fun providesRepository(taskDao: TaskDao) : TaskRepositoryModule {
        return TaskRepositoryModule(taskDao)
    }
}