package com.gabmarquez.taskroom.utilities

import android.view.View

interface TaskEventClick {
    fun onTaskClick(view: View, position: Int, isLongClick: Boolean)
}