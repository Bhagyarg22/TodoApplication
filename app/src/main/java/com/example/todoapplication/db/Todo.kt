package com.example.todoapplication.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey val id: Int,
    val userId: Int,
    val title: String,
    var completed: Boolean
)

