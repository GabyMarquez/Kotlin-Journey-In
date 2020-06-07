package com.gabmarquez.taskroom.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gabmarquez.taskroom.databinding.FragmentListTaskBinding
import com.gabmarquez.taskroom.view.list.adapter.TaskListAdapter
import com.gabmarquez.taskroom.view.list.adapter.TaskListListener
import com.gabmarquez.taskroom.viewmodel.ListTaskViewModel
import com.gabmarquez.taskroom.viewmodel.TaskViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ListTask : DaggerFragment() {

    private lateinit var taskListAdapter: TaskListAdapter
    private lateinit var taskViewModel: ListTaskViewModel

    @Inject
    lateinit var taskViewModelFactory: TaskViewModelFactory
    private lateinit var binding: FragmentListTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListTaskBinding.inflate(inflater)

        setViewModel()
        setUpRecyclerView()
        populateRecyclerView()

        binding.addTask.setOnClickListener {
            insertTask()
        }

        return binding.root
    }

    private fun setViewModel() {
        taskViewModel =
            ViewModelProvider(this, taskViewModelFactory).get(ListTaskViewModel::class.java)
    }

    private fun setUpRecyclerView() {
        taskListAdapter = TaskListAdapter(TaskListListener { task ->
            taskViewModel.onTaskClicked(task)
            goingToAddTask()
        })

        binding.recyclerviewListTask.adapter = taskListAdapter
    }

    private fun populateRecyclerView() {
        taskViewModel.list.observe(viewLifecycleOwner, Observer { taskList ->
            taskList?.let { taskList ->
                taskListAdapter.submitList(taskList)
            }
        })
    }

    private fun insertTask() {
        this.findNavController().navigate(ListTaskDirections.actionListTaskToDetailAddTask())
    }

    private fun goingToAddTask() {
        taskViewModel.navigateToEditTask.observe(viewLifecycleOwner, Observer { task ->
            task?.let {
                this.findNavController()
                    .navigate(ListTaskDirections.actionListTaskToDetailEditTask(task))
            }
        })
    }
}