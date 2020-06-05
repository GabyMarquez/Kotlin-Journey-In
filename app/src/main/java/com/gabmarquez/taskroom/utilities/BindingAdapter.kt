package com.gabmarquez.taskroom.utilities

import android.content.res.Resources
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.gabmarquez.taskroom.repository.local.Task
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


//@BindingAdapter("taskTitle")
//fun EditText.taskTitle(task: Task?) {
//    task?.let {
//        text = convertDurationToFormatted(task.title)
//    }
//}
//
////@BindingAdapter("taskTitle")
////fun taskTitle(editText: EditText, task: Task?) {
////    editText.setText(task!!.title)
////}
//
////@BindingAdapter("taskTitle")
////fun taskTitle(view: EditText, task: Task) {
////    // Some checks removed for clarity
////    view.text = task.title
////}
//
//fun convertDurationToFormatted(title: String) : String {
//    val titleTask = title
//
//    return titleTask
//    }
//}