package com.gabmarquez.taskroom.view.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.gabmarquez.taskroom.R
import com.gabmarquez.taskroom.repository.local.Task
import com.gabmarquez.taskroom.utilities.TaskEventClick
import com.gabmarquez.taskroom.viewmodel.ListTaskViewModel
import com.gabmarquez.taskroom.viewmodel.ListTaskViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_list_task.*
import javax.inject.Inject

class ListTask : DaggerFragment(), TaskEventClick {

    private lateinit var taskViewModel : ListTaskViewModel
    private lateinit var taskListAdapter : TaskListAdapter
    @Inject
    lateinit var listTaskViewModelFactory : ListTaskViewModelFactory
    private lateinit var listTask : List<Task>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listTask = mutableListOf()
        return inflater.inflate(R.layout.fragment_list_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewModel()
        initRecyclerView()
        populateRecyclerView()

        add_task.setOnClickListener {
            val navDirection = ListTaskDirections.actionListTaskToDetailAddTask()
            findNavController().navigate(navDirection)
//            val intent = Intent(getActivity(), DetailTask::class.java)
//            startActivityForResult(intent, Handle.INTENT_CREATE)
        }
    }

    private fun setViewModel() {
        taskViewModel = ViewModelProvider(this, listTaskViewModelFactory).get(ListTaskViewModel::class.java)
    }

    private fun initRecyclerView() {
        recyclerview_list_task.layoutManager = GridLayoutManager(getActivity(), 2)
        taskListAdapter = TaskListAdapter()
        recyclerview_list_task.adapter = taskListAdapter
        taskListAdapter.setTaskEvent(this)
    }

    private fun populateRecyclerView() {
        taskViewModel.getListTask().observe(viewLifecycleOwner, Observer {
                taskList ->
            taskList?.let {
                taskListAdapter.setTask(it)
            }
        })
    }

    override fun onTaskClick(view: View, position: Int) {
        Log.d("onTaskClick", "Position: $position")
        Toast.makeText(requireContext(), "You will be able to update here", Toast.LENGTH_SHORT).show()
        findNavController().navigate(ListTaskDirections.actionListTaskToDetailEditTask())
//        findNavController().navigate(
//            SeriesListFragmentDirections.actionSeriesListFragmentToMangaDetailFragment(manga.id)
//        )
    }
}