package com.tolgacobanoglu.taskmanager.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.tolgacobanoglu.taskmanager.database.Entity
import com.tolgacobanoglu.taskmanager.database.TaskDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TasksViewModel(application: Application, val database: TaskDatabase) :
    AndroidViewModel(application) {

    var tasks: LiveData<List<Task>>
    private var list: MutableList<Task> = emptyList<Task>().toMutableList()

    init {
        tasks = getAllTasks()
    }

    fun updateDatabase()
    {
        tasks = getAllTasks()
    }

    fun addData(task: Task) {
        CoroutineScope(Dispatchers.IO).launch {
            list.add(task)
        }
    }

    fun getAllTasks(): LiveData<List<Task>> {
        return database.dao().getTasks()
    }

    fun deleteAllTasks() {
        CoroutineScope(Dispatchers.IO).launch {
            database.dao().deleteAll()
            updateDatabase()
        }

    }

    fun getTask(pos: Int): Task {
        return database.dao().getTaskById(pos)
    }


    fun updateTask(pos:Int, title: String,content:String,priority:String)
    {
        tasks.value!!.get(pos).title = title
        tasks.value!!.get(pos).priority = priority
        tasks.value!!.get(pos).content = content
    }

    fun deleteTask(pos: Int) {
        database.dao().deleteTask(Entity(pos,
            tasks.value!!.get(pos).title, tasks.value!!.get(pos).content, tasks.value!!.get(pos).priority))
        //updateDatabase()

    }

}