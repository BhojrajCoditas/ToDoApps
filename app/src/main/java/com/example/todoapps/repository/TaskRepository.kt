package com.example.todoapps.repository

import androidx.lifecycle.LiveData
import com.example.todoapps.data.Task
import com.example.todoapps.data.TaskDao

class TaskRepository(private val dao: TaskDao) {

    val allTasks: LiveData<List<Task>> = dao.getAllTasks()
    val todayTasks: LiveData<List<Task>> = dao.getTodayTasks()
    val tomorrowTasks: LiveData<List<Task>> = dao.getTomorrowTasks()

    suspend fun insert(task: Task) = dao.insertTask(task)
    suspend fun update(task: Task) = dao.updateTask(task)
    suspend fun delete(task: Task) = dao.deleteTask(task)
}
