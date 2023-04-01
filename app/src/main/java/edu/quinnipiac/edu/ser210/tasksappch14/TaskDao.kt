package edu.quinnipiac.edu.ser210.tasksappch14

import androidx.lifecycle.LiveData
import androidx.room.*

//tells room this is for Data access
@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task:Task)

    @Update
    suspend fun update(task:Task)

    //adding suspend initiates a coroutine
    @Delete
    suspend fun delete(task:Task)

    @Query("SELECT * FROM task_table WHERE taskId = :taskId")
    fun get(taskId:Long):LiveData<Task>

    @Query("SELECT * FROM task_table ORDER BY taskId DESC")
    fun getAll():LiveData<Task>
}
