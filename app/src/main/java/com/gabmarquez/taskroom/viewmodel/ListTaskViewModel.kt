package com.gabmarquez.taskroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gabmarquez.taskroom.repository.TaskRepository
import com.gabmarquez.taskroom.repository.local.Task
import javax.inject.Inject

class ListTaskViewModel @Inject constructor (val taskRepository: TaskRepository, application: Application) : AndroidViewModel(application) {

    val list = taskRepository.getListTask()
    val navigateToEditTask  get() = _navigateToEditTask

    fun getListTask(): LiveData<List<Task>> {
        return taskRepository.getListTask()
    }

    fun insertTask(task: Task) {
        taskRepository.insertTask(task)
    }

    fun updateTask(task: Task) {
        taskRepository.updateTask(task)
    }

    private val _navigateToEditTask = MutableLiveData<Long>()

    fun onTaskClicked(taskId: Long?) {
        _navigateToEditTask.value = taskId
    }
}