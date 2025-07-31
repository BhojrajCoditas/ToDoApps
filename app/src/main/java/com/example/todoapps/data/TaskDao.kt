package com.example.todoapps.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): LiveData<List<Task>>

    // Filtered queries to load tasks by 'isToday'
    @Query("SELECT * FROM tasks WHERE isToday = 1")
    fun getTodayTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM tasks WHERE isToday = 0")
    fun getTomorrowTasks(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
}
