package com.example.memento

import com.example.memento.entities.Task
import com.example.memento.entities.TaskCategory
import java.util.Date

object MockDataLoader {
    val categories = getDemoCategories()
    fun getDemoData(): MutableList<Task> = mutableListOf(
        Task(
            "Submit seminar",
            Date(),
            categories[0],
            false
        ),
        Task(
            "Prepare for exercises",
            Date(),
            categories[1],
            false
        ),
        Task(
            "Connect to server (SSH)",
            Date(),
            categories[2],
            false
        )
    )

    fun getDemoCategories(): List<TaskCategory> = listOf(
        TaskCategory("RAMPU", "#000080"),
        TaskCategory("RPP", "#FF0000"),
        TaskCategory("RWA", "#CCCCCC")
    )
}









