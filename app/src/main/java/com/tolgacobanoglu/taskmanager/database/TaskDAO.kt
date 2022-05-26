package com.tolgacobanoglu.taskmanager.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tolgacobanoglu.taskmanager.model.Task

@Dao
interface TaskDAO
{
    @Insert
    suspend fun insertTask(entity: Entity)

    @Update
     fun updateTask(entity: Entity)

    @Delete
     fun deleteTask(entity: Entity)

    @Query("Delete from tasks")
     fun deleteAll()

    @Query("Select * from tasks where id = :targetID")
     fun getTaskById( targetID: Int) : Task

    @Query("Select * from tasks")
     fun getTasks(): LiveData<List<Task>>


}