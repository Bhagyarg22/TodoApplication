package com.example.todoapplication.repositroy

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.todoapplication.Network.TodoApi
import com.example.todoapplication.db.Todo
import com.example.todoapplication.db.TodoDao
import com.example.todoapplication.model.TodoResponse
import com.example.todoapplication.model.TodoResponseItem
import javax.inject.Inject


class TodoRepo @Inject constructor(private val todoDao: TodoDao, private val todoApiService: TodoApi) {
    val allTodos: LiveData<List<Todo>> = todoDao.getAllTodos()

    suspend fun updateTodoStatus(todo: Todo) {
        todoDao.updateTodoStatus(todo)
    }

    suspend fun fetchAndStoreTodos() {
        val response = todoApiService.getTodos()
        if (response.isNotEmpty()) {
            val todos = response
            val todoEntities = todos.map { todo ->
                Todo(
                    id = todo.id,
                    userId = todo.userId,
                    title = todo.title,
                    completed = todo.completed
                )
            }
            todoDao.insertAll(todoEntities)
        }
    }
}