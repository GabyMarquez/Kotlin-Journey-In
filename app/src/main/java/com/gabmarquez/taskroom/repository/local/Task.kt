package com.gabmarquez.taskroom.repository.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_room_db")
data class Task(

    @PrimaryKey(autoGenerate = true)
    val idTask: Long?,

    @ColumnInfo(name = "title")
    val title: String = "",

    @ColumnInfo(name = "description")
    val description: String = "",

    @ColumnInfo(name = "date")
    val date: String = ""
)