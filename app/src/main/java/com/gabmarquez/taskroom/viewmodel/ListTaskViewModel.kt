package com.gabmarquez.taskroom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabmarquez.taskroom.repository.TaskRepositoryModule
import com.gabmarquez.taskroom.repository.local.Task
import javax.inject.Inject

class ListTaskViewModel @Inject constructor (val taskRepositoryModule: TaskRepositoryModule) : ViewModel() {

    val list = getListTask()
    val navigateToEditTask : LiveData<Task> get() = _navigateToEditTask

    fun getListTask(): LiveData<List<Task>> {
        return taskRepositoryModule.getListTask()
    }

    fun insertTask(task: Task) {
        taskRepositoryModule.insertTask(task)
    }

    fun updateTask(task: Task) {
        taskRepositoryModule.updateTask(task)
    }

    private val _navigateToEditTask = MutableLiveData<Task>()
}