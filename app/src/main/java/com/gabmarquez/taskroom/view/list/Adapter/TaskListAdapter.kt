package com.gabmarquez.taskroom.view.list.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gabmarquez.taskroom.databinding.ItemTaskBinding
import com.gabmarquez.taskroom.repository.local.Task

class TaskListAdapter (val clickListener : TaskListListener) : ListAdapter<Task, TaskListAdapter.ViewHolder> (TaskListDiffCallback()) {

    class ViewHolder private constructor(val itemTaskBinding: ItemTaskBinding) : RecyclerView.ViewHolder(itemTaskBinding.root) {
        fun bind(clickListener: TaskListListener, task: Task) {
            itemTaskBinding.task = task
//            itemTaskBinding.clickListener = clickListener
            itemTaskBinding.executePendingBindings()
        }

        companion object {
            fun from (parent: ViewGroup) : ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val itemTaskBinding = ItemTaskBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(itemTaskBinding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val taskPosition = getItem(position)
//        holder.bind(taskPosition)
        holder.bind(clickListener, taskPosition!!)
    }
}



