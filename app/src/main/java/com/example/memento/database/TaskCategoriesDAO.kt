package com.example.memento.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.memento.entities.TaskCategory

@Dao
interface TaskCategoriesDAO {
    @Query("SELECT * FROM task_categories")
    fun getAllCategories(): List<TaskCategory>
    @Query("SELECT * FROM task_categories WHERE id = :id")
    fun getCategoryById(id: Int): TaskCategory
    @Insert
    fun insertCategory(vararg category: TaskCategory)
}
