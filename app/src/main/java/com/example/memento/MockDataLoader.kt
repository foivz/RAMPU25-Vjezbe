package com.example.memento

import com.example.memento.database.TasksDatabase
import com.example.memento.entities.Task
import com.example.memento.entities.TaskCategory
import java.util.Date

object MockDataLoader {
    val categories = getDemoCategories()
    fun getDemoCategories(): List<TaskCategory> = listOf(
        TaskCategory(1, "RAMPU", "#000080"),
        TaskCategory(2, "RPP", "#FF0000"),
        TaskCategory(3, "RWA", "#CCCCCC")
    )

    fun loadMockData() {
        val tasksDao = TasksDatabase.getInstance().getTasksDao()
        val taskCategoriesDao = TasksDatabase.getInstance().getTaskCategoriesDao()

        if (
            tasksDao.getAllTasks(false).isEmpty() &&
            tasksDao.getAllTasks(true).isEmpty() &&
            taskCategoriesDao.getAllCategories().isEmpty()
        ) {
            val categories = arrayOf(
                TaskCategory(1, "RAMPU", "#000080"),
                TaskCategory(2, "RPP", "#FF0000"),
                TaskCategory(3, "RWA", "#CCCCCC")
            )
            taskCategoriesDao.insertCategory(*categories)

            val dbCategories = taskCategoriesDao.getAllCategories()

            val tasks = arrayOf(
                Task(
                    1,
                    "Submit a seminar paper",
                    Date(),
                    dbCategories[0].id,
                    false
                ),
                Task(
                    2,
                    "Prepare for exercises",
                    Date(),
                    dbCategories[1].id,
                    false
                ),
                Task(
                    3,
                    "Work on 1st homework",
                    Date(),
                    dbCategories[2].id,
                    false
                )
            )
            tasksDao.insertTask(*tasks)
        }
    }
}
