package com.gabmarquez.taskroom.view.detail

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.gabmarquez.taskroom.R
import com.gabmarquez.taskroom.databinding.FragmentDetailTaskBinding
import com.gabmarquez.taskroom.repository.local.Task
import com.gabmarquez.taskroom.viewmodel.ListTaskViewModel
import com.gabmarquez.taskroom.viewmodel.ListTaskViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_detail_task.*
import java.util.*
import javax.inject.Inject

class DetailEditTask : DaggerFragment() {

    var task: Task? = null
    private lateinit var binding: FragmentDetailTaskBinding

    private lateinit var taskViewModel : ListTaskViewModel
    @Inject
    lateinit var listTaskViewModelFactory : ListTaskViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_task, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()

        binding.btnSave.setOnClickListener {
            saveTask()
        }

        binding.btnCalendar.setOnClickListener {
            pickerCalendar()
        }

        binding.btnBack.setOnClickListener {
            popStack()
        }

        binding.btnInsert.setOnClickListener {
            event()
        }

        if (task != null) btn_insert.visibility = View.VISIBLE
    }

    private fun setupViewModel() {
        taskViewModel = ViewModelProvider(this, listTaskViewModelFactory).get(ListTaskViewModel::class.java)
    }

    private fun saveTask() {
        if (validateTask(1)) {
            var title = binding.edtTitle.text.toString()
            var description = binding.edtDescription.text.toString()
            var date = binding.edtDate.text.toString()

            val idTask = if (task != null) task?.idTask else null

            val task = Task(idTask = idTask, title = title, description = description, date = date)
            taskViewModel.insertTask(task)
            popStack()
        }
    }

    private fun validateTask(option: Int): Boolean {
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
        if (option == 1) {
            if (binding.edtDate.text.isEmpty()) {
                binding.edtDate.requestFocus()
                snack(getString(R.string.error_date))
                return false
            }
        }

        return true
    }

    private fun snack(message: String) {
        val snackbar = Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG)
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(Color.parseColor("#969CFF"))
        snackbar.show()
    }

    private fun pickerCalendar() {

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val pickerDialog = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

            edt_date.setText("" + dayOfMonth + "/" + month + "/" + year)

        }, year, month, day)

        pickerDialog.show()
    }

    fun popStack() {
        val navDirection = DetailEditTaskDirections.actionDetailEditTaskToListTask()
        findNavController().navigate(navDirection)
    }

    private fun event() {
        if (validateTask(0)) {
            var title =  binding.edtTitle.text.toString()
            var description = binding.edtDescription.text.toString()

            addEvent(title, description)
        }
    }

    private fun addEvent(title: String, description: String ) {
        val intent = Intent(Intent.ACTION_INSERT)
            .setData(CalendarContract.Events.CONTENT_URI)
            .putExtra(CalendarContract.Events.TITLE, title)
            .putExtra(CalendarContract.Events.DESCRIPTION, description)

        startActivity(intent)
    }
}