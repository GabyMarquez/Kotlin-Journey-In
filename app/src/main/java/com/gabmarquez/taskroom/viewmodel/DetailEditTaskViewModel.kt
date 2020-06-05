package com.gabmarquez.taskroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import com.gabmarquez.taskroom.repository.DetailEditTaskRepository
import com.gabmarquez.taskroom.repository.TaskRepository
import com.gabmarquez.taskroom.repository.local.Task
import com.gabmarquez.taskroom.repository.local.TaskDao
import javax.inject.Inject

class DetailEditTaskViewModel @Inject constructor(taskDao: TaskDao, application: Application) : AndroidViewModel(application) {

    private val task = MediatorLiveData<Task>()
    private val taskId: Long = 0L


    fun getTask() = task

    init {
        task.addSource(taskDao.getTaskWithId(taskId), task::setValue)
//        task.addSource(database.getNightWithId(sleepNightKey), night::setValue)
    }
}
