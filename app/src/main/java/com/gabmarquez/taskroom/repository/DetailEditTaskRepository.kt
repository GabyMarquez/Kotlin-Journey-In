package com.gabmarquez.taskroom.repository

import androidx.lifecycle.LiveData
import com.gabmarquez.taskroom.repository.local.Task
import com.gabmarquez.taskroom.repository.local.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailEditTaskRepository @Inject constructor(val taskDao: TaskDao) {

    fun getTaskWithId(taskId: Long): LiveData<Task> {
        return taskDao.getTaskWithId(taskId)
    }

    suspend fun insertTask(task: Task) {
        withContext(Dispatchers.IO) {
            taskDao.insertTask(task)
        }
    }
}