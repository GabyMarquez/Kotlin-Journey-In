package com.gabmarquez.taskroom.view.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.gabmarquez.taskroom.repository.local.Task

class TaskListDiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.idTask == newItem.idTask
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}