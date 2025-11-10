package com.example.memento.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("task_categories")
data class TaskCategory(
    @PrimaryKey(true) val id: Int,
    val name: String,
    val color: String
) {
    override fun toString(): String {
        return name
    }
}
