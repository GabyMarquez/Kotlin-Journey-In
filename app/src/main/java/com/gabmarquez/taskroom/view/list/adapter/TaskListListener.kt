package com.gabmarquez.taskroom.view.list.adapter

import com.gabmarquez.taskroom.repository.local.Task

class TaskListListener(val clickListener: (title: Long?) -> Unit) {
    fun onClick(task: Task) = clickListener(task.idTask)
}