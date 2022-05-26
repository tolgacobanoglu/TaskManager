package com.tolgacobanoglu.taskmanager.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Entity
(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var content: String,
    var priority:String
)