package com.gabmarquez.taskroom.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import javax.inject.Singleton

@Singleton
@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao() : TaskDao

//    companion object {
//
//        @Volatile private var DATABASE : TaskDatabase? = null
//
//        fun getDatabase(context: Context): TaskDatabase? {
//            synchronized(this) {
//                var instance = DATABASE
//                if (instance == null) {
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        TaskDatabase::class.java,
//                        "task_room_db"
//                    ).fallbackToDestructiveMigration()
//                        .build()
//                    DATABASE = instance
//                }
//                return instance
//            }
//        }
//    }
}