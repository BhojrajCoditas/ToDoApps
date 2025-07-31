package com.example.todoapps.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tasks")
data class Task (
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    val name: String,
    val time: String,
    val isToday: Boolean,
    var isDone: Boolean = false,
    val isCompleted: Boolean = false
)



