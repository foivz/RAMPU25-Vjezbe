package com.example.memento.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.memento.database.TasksDatabase
import com.example.memento.helpers.DateConverter
import java.util.Date

@Entity(
    "tasks",
    foreignKeys = [ForeignKey(
        entity = TaskCategory::class,
        parentColumns = ["id"],
        childColumns = ["category_id"],
        onDelete = ForeignKey.RESTRICT
    )]
)
@TypeConverters(DateConverter::class)
data class Task(
    @PrimaryKey(true) val id: Int,
    val name: String,
    @ColumnInfo("due_date") val dueDate: Date,
    @ColumnInfo("category_id", index = true) val categoryId: Int,
    var completed: Boolean
) {
    @delegate:Ignore
    val category: TaskCategory by lazy {
        TasksDatabase.getInstance().getTaskCategoriesDao().getCategoryById(categoryId)
    }
}
