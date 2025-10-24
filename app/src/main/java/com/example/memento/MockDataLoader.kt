package com.example.memento

import com.example.memento.entities.Task
import com.example.memento.entities.TaskCategory
import java.util.Date

object MockDataLoader {
    fun getDemoData(): List<Task> = listOf(
        Task(
            "Submit seminar",
            Date(),
            TaskCategory("RAMPU", "#000080"),
            false
        ),
        Task(
            "Prepare for exercises",
            Date(),
            TaskCategory("RPP", "#FF0000"),
            false
        ),
        Task(
            "Connect to server (SSH)",
            Date(),
            TaskCategory("RWA", "#CCCCCC"),
            false
        )
    )
}