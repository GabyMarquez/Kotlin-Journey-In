package com.gabmarquez.taskroom.view.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gabmarquez.taskroom.R
import com.gabmarquez.taskroom.databinding.ItemTaskBinding
import com.gabmarquez.taskroom.repository.local.Task
import com.gabmarquez.taskroom.utilities.TaskEventClick
import kotlinx.android.synthetic.main.item_task.view.*

class TaskListAdapter () : RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    private var taskList: List<Task> = arrayListOf()
    private lateinit var taskEventClick: TaskEventClick

    class ViewHolder(binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        var itemTaskBinding: ItemTaskBinding = binding
//        fun bind(task: Task) {
//            itemView.title.text = task.title
//            itemView.description.text = task.description
//            itemView.date.text = task.date
//        }

        private lateinit var taskEventClick: TaskEventClick
//        val task: Task

        init {
            itemTaskBinding.root.setOnClickListener(this)
        }

        fun setTaskEvent(taskEventClick: TaskEventClick) {
            this.taskEventClick = taskEventClick
        }

        override fun onClick(v: View?) {
            if (v != null) {
                taskEventClick.onTaskClick(v, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemTaskBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_task, parent, false)
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = taskList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(taskList[position])
        val task: Task = taskList[position]

        holder.itemTaskBinding.task = task
        holder.setTaskEvent(taskEventClick)
    }

    fun setTaskEvent(taskEventClick: TaskEventClick) {
        this.taskEventClick = taskEventClick
    }

    fun setTask(taskList: List<Task>) {
        this.taskList = taskList
        notifyDataSetChanged()
    }
}

