package com.gabmarquez.taskroom.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabmarquez.taskroom.repository.TaskRepositoryModule
import com.gabmarquez.taskroom.repository.local.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListTaskViewModel @Inject constructor (val taskRepositoryModule: TaskRepositoryModule) : ViewModel() {

    val taskListDb: MutableLiveData<List<Task>> = MutableLiveData() //late

    fun getListTask(): LiveData<List<Task>> {
        return taskRepositoryModule.getListTask()
    }

    fun insertTask(task: Task) {
        taskRepositoryModule.insertTask(task)
    }

    fun updateTask(task: Task) {
        taskRepositoryModule.updateTask(task)
    }
//        try {
//            CoroutineScope(Dispatchers.IO).launch {
//
//                val data = taskRepository.getAllTaskFromDb()
//                taskListFromDb.postValue(data)
//
//            }
//        } catch (error: Exception) {
//            Log.e(TAG, error.message.toString())
//        }
//    }
}