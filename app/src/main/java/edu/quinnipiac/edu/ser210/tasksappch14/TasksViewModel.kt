package edu.quinnipiac.edu.ser210.tasksappch14

import android.annotation.SuppressLint
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TasksViewModel(val dao: TaskDao): ViewModel(){
    //for the new taskName
    var newTaskName = ""

    //get the records from the database
    private val tasks = dao.getAll()
    val tasksString = Transformations.map(tasks){
        //transforms the tasks into a livedata<string> which is assigned to tasksString.
        //As this is a live data object, it will automatically get updated if the data changes
        tasks -> formatTasks(tasks)
    }

    //creates a task object and uses the TaskDao insert method to insert it into the
    //database
    @SuppressLint("SuspiciousIndentation")
    fun addTask(){
        //for coroutine
        viewModelScope.launch{
        val task = Task()
        task.taskName = newTaskName
            dao.insert(task)
        }

    }

    fun formatTasks(tasks: List<Task>): String{
        return tasks.fold(""){
            str, item ->str + '\n' + formatTask(item)
        }
    }

    fun formatTask(task:Task):String{
        var str = "ID: ${task.taskId}"
        str += '\n' + "Name: ${task.taskName}"
        str += '\n' + "Complete: ${task.taskDone}" + '\n'
        return str
    }

}