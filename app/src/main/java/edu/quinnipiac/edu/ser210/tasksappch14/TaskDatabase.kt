package edu.quinnipiac.edu.ser210.tasksappch14

import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//it adds the table defined in the Task class to the database
@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase: RoomDatabase() {
    abstract val taskDao: TaskDao

    //used to call the getInstance() method using TaskDatabase.getInstance()
    companion object{
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        //returns an instance of TaskDatabase. It builds the database if it doesnt yet exist
        fun getInstance(context: Context): TaskDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDatabase::class.java,
                        "tasks_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}