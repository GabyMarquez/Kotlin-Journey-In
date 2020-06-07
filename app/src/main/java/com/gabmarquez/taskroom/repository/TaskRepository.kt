package com.gabmarquez.taskroom.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gabmarquez.taskroom.repository.local.Task
import com.gabmarquez.taskroom.repository.local.TaskDao
import com.gabmarquez.taskroom.repository.local.TaskDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskRepository @Inject constructor(val taskDao: TaskDao) {

    fun getListTask(): LiveData<List<Task>> {
        return taskDao.getListTask()
    }
}