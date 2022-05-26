package com.tolgacobanoglu.taskmanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Entity::class],version=1, exportSchema = false)
 abstract class TaskDatabase : RoomDatabase()
{
    abstract fun dao() : TaskDAO

    companion object{
        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase{
            val tempInstance = INSTANCE

            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_manager"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}