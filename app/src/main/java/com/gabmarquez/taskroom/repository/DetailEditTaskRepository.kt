package com.gabmarquez.taskroom.repository

import androidx.lifecycle.LiveData
import com.gabmarquez.taskroom.repository.local.Task
import com.gabmarquez.taskroom.repository.local.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DetailEditTaskRepository @Inject constructor(val taskDao: TaskDao) {

    fun getTaskWithId(taskId: Long) = runBlocking {
        this.launch(Dispatchers.IO) {
            taskDao.getTaskWithId(taskId)
        }
    }
}