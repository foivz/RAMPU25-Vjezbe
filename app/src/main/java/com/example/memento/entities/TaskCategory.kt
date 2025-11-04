package com.example.memento.entities

data class TaskCategory(val name: String, val color: String) {
    override fun toString(): String {
        return name
    }
}