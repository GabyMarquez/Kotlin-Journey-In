package com.gabmarquez.taskroom.view.detail

import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gabmarquez.taskroom.R
import com.gabmarquez.taskroom.databinding.FragmentAddTaskBinding
import com.gabmarquez.taskroom.repository.local.Task
import com.gabmarquez.taskroom.utilities.EventObserver
import com.gabmarquez.taskroom.utilities.setupSnackbar
import com.gabmarquez.taskroom.viewmodel.DetailTaskViewModel
import com.gabmarquez.taskroom.viewmodel.TaskViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_add_task.*
import java.util.*
import javax.inject.Inject


class DetailAddTask : DaggerFragment() {

    var task: Task? = null

    private lateinit var binding: FragmentAddTaskBinding

    private lateinit var taskViewModel: DetailTaskViewModel

    @Inject
    lateinit var taskViewModelFactory: TaskViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_add_task, container, false)
        setupViewModel()
        binding = FragmentAddTaskBinding.bind(root).apply {
            this.detailViewModel = taskViewModel
        }
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.handlers = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskViewModel.navigateToListTask.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it == true) {
                val navDirection = DetailAddTaskDirections.actionDetailAddTaskToListTask()
                findNavController().navigate(navDirection)
                taskViewModel.doneNavigating()
            }
        })
    }

    private fun setupViewModel() {
        taskViewModel =
            ViewModelProvider(this, taskViewModelFactory).get(DetailTaskViewModel::class.java)
    }

    fun saveTask() {
        if (validateTask()) {
            var title = binding.edtTitle.text.toString()
            var description = binding.edtDescription.text.toString()
            var date = binding.edtDate.text.toString()

            val idTask = if (task != null) task?.idTask else null

            val task = Task(idTask, title, description, date)
            taskViewModel.insertTask(task)
        }
    }

    private fun validateTask(): Boolean {
        if (binding.edtTitle.text.isEmpty()) {
            binding.edtTitle.requestFocus()
            snack(getString(R.string.error_title))
            return false
        }
        if (binding.edtDescription.text.isEmpty()) {
            binding.edtDescription.requestFocus()
            snack(getString(R.string.error_description))
            return false
        }
        if (binding.edtDate.text.isEmpty()) {
            binding.edtDate.requestFocus()
            snack(getString(R.string.error_date))
            return false
        }

        return true
    }

    private fun snack(message: String) {
        val snackbar = Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG)
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))
        snackbar.show()
    }

    fun pickerCalendar() {

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val pickerDialog = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                binding.edtDate.setText("" + dayOfMonth + "/" + month + "/" + year)
            },
            year,
            month,
            day
        )

        pickerDialog.show()
    }
}