package com.gabmarquez.taskroom.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.gabmarquez.taskroom.R
import com.gabmarquez.taskroom.repository.DetailEditTaskRepository
import com.gabmarquez.taskroom.repository.local.Task
import com.gabmarquez.taskroom.repository.local.TaskDao
import com.gabmarquez.taskroom.utilities.AbsentLiveData
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailTaskViewModel @Inject constructor(
    val taskRepository: DetailEditTaskRepository,
    application: Application
) : AndroidViewModel(application) {

    val taskId: MutableLiveData<Long> = MutableLiveData()
    val taskLive: LiveData<Task>

    private val _navigateToListTask = MutableLiveData<Boolean?>()

    val navigateToListTask: LiveData<Boolean?>
    get() = _navigateToListTask

    init {
        taskLive = taskId.switchMap {
            taskId.value?.let { task ->
                taskRepository.getTaskWithId(task)
            } ?: AbsentLiveData.create()
        }
    }

    fun getIdTask(idTask: Long) {
        taskId.value = idTask
    }

    fun insertTask(task: Task) {
        viewModelScope.launch {
            taskRepository.insertTask(task)
            _navigateToListTask.value = true
        }
    }

    fun backToListTask() {
        _navigateToListTask.value = true
    }

    fun doneNavigating() {
        _navigateToListTask.value = null
    }
}
