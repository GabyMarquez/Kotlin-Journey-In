package com.gabmarquez.taskroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gabmarquez.taskroom.repository.TaskRepository
import com.gabmarquez.taskroom.repository.local.Task
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListTaskViewModel @Inject constructor(
    val taskRepository: TaskRepository,
    application: Application
) : AndroidViewModel(application) {

    val list = taskRepository.getListTask()
    val navigateToEditTask get() = _navigateToEditTask

    private val _navigateToEditTask = MutableLiveData<Long>()

    fun onTaskClicked(taskId: Long?) {
        _navigateToEditTask.value = taskId
    }
}