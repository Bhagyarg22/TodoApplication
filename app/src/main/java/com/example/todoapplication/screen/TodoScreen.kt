package com.example.todoapplication.screen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text


import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.todoapplication.db.Todo
import com.example.todoapplication.viewmodel.TodoViewmodel


@Composable
fun TodoListScreen(todoViewModel: TodoViewmodel = viewModel()) {
    val todos by todoViewModel.allTodos.observeAsState(emptyList())

    LazyColumn {
        items(todos) { todo ->
            TodoItem(todo, onTodoStatusChanged = {
                todoViewModel.updateTodoStatus(it)
            })
        }
    }


}

@Composable
fun TodoItem(todo: Todo, onTodoStatusChanged: (Todo) -> Unit) {
    // Implement the UI for a single todo item, including checkbox to change status

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = todo.title,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = if (todo.completed) androidx.compose.ui.graphics.Color.Gray else androidx.compose.ui.graphics.Color.Black
        )

        Checkbox(
            checked = todo.completed,
            onCheckedChange = { isChecked ->
                val updatedTodo = todo.copy(completed = isChecked)
                onTodoStatusChanged(updatedTodo)
            }
        )
    }
}

