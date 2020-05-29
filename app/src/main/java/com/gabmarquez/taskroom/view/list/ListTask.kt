package com.gabmarquez.taskroom.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gabmarquez.taskroom.databinding.FragmentListTaskBinding
import com.gabmarquez.taskroom.view.list.Adapter.TaskListAdapter
import com.gabmarquez.taskroom.view.list.Adapter.TaskListListener
import com.gabmarquez.taskroom.viewmodel.ListTaskViewModel
import com.gabmarquez.taskroom.viewmodel.ListTaskViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_list_task.*
import javax.inject.Inject

class ListTask : DaggerFragment() {

    private lateinit var taskListAdapter : TaskListAdapter
    private lateinit var taskViewModel : ListTaskViewModel
    @Inject
    lateinit var listTaskViewModelFactory : ListTaskViewModelFactory
    private lateinit var binding: FragmentListTaskBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

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
        taskViewModel = ViewModelProvider(this, listTaskViewModelFactory).get(ListTaskViewModel::class.java)
    }

    private fun setUpRecyclerView() {
        taskListAdapter = TaskListAdapter(TaskListListener {
            Toast.makeText(context, "${it}", Toast.LENGTH_LONG).show()
        })

        binding.recyclerviewListTask.adapter = taskListAdapter
    }

    private fun populateRecyclerView() {
        taskViewModel.list.observe(viewLifecycleOwner, Observer {
            it?.let {
                taskListAdapter.submitList(it)
            }
        })
    }

    private fun insertTask() {
        val navDirection = ListTaskDirections.actionListTaskToDetailAddTask()
        findNavController().navigate(navDirection)
    }
}