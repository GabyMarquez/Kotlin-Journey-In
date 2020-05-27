package com.gabmarquez.taskroom.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.gabmarquez.taskroom.repository.local.Task
import com.gabmarquez.taskroom.repository.local.TaskDao
import com.gabmarquez.taskroom.repository.local.TaskDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TaskRepository (private val taskDatabase: TaskDatabase) {

    val taskList: LiveData<List<Task>> = taskDatabase.taskDao().getListTask()
}