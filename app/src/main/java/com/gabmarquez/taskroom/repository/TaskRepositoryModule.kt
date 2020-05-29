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
import javax.inject.Inject

class TaskRepositoryModule @Inject constructor(val taskDao: TaskDao) {

    val taskList : LiveData<List<Task>> = taskDao.getListTask()

    fun getListTask(): LiveData<List<Task>> {
        return taskDao.getListTask()
    }

    fun insertTask(task: Task) = runBlocking {
        this.launch (Dispatchers.IO) {
            taskDao.insertTask(task)
        }
    }

    fun updateTask(task: Task) = runBlocking {
        this.launch(Dispatchers.IO) {
            taskDao.updateTask(task)
        }
    }
}