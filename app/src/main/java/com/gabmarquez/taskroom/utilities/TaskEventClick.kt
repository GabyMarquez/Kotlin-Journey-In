package com.gabmarquez.taskroom.utilities

import android.view.View
import com.gabmarquez.taskroom.repository.local.Task

interface TaskEventClick {
    fun onTaskClick(view: View, position: Int)
}