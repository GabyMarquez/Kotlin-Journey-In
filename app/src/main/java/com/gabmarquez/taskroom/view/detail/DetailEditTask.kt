package com.gabmarquez.taskroom.view.detail

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gabmarquez.taskroom.R
import com.gabmarquez.taskroom.databinding.FragmentAddTaskBinding
import com.gabmarquez.taskroom.databinding.FragmentDetailTaskBinding
import com.gabmarquez.taskroom.repository.local.Task
import com.gabmarquez.taskroom.utilities.observeLiveData
import com.gabmarquez.taskroom.viewmodel.DetailTaskViewModel
import com.gabmarquez.taskroom.viewmodel.TaskViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DetailEditTask : DaggerFragment() {

    private lateinit var binding: FragmentDetailTaskBinding

    private lateinit var taskViewModel: DetailTaskViewModel

    private lateinit var arguments: DetailEditTaskArgs

    @Inject
    lateinit var taskViewModelFactory: TaskViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_detail_task, container, false)
        arguments = DetailEditTaskArgs.fromBundle(requireArguments())
        setupViewModel()
        binding = FragmentDetailTaskBinding.bind(root).apply {
            this.detailViewModel = taskViewModel
        }
        binding.handlers = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskViewModel.navigateToListTask.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it == true) {
                val navDirection = DetailEditTaskDirections.actionDetailEditTaskToListTask()
                findNavController().navigate(navDirection)
                taskViewModel.doneNavigating()
            }
        })
    }

    private fun setupViewModel() {
        taskViewModel = ViewModelProvider(
            this,
            taskViewModelFactory
        ).get(DetailTaskViewModel::class.java)
        taskViewModel.getIdTask(arguments.taskId)
        observeLiveData(taskViewModel.taskLive) { task ->
            init(task)
        }
    }

    private fun init(task: Task) {
        binding.task = task
    }

    fun createEvent(title: String, description: String) {
        val intent = Intent(Intent.ACTION_INSERT)
            .setData(CalendarContract.Events.CONTENT_URI)
            .putExtra(CalendarContract.Events.TITLE, title)
            .putExtra(CalendarContract.Events.DESCRIPTION, description)

        startActivity(intent)
    }
}