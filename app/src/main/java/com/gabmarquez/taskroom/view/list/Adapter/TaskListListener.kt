package com.gabmarquez.taskroom.view.list.Adapter

import com.gabmarquez.taskroom.repository.local.Task

class TaskListListener(val clickListener: (taskId: Long) -> Unit) {
    fun onClick(task: Task) = clickListener(task.idTask!!)
}