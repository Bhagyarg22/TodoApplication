package com.example.todoapplication.viewmodel



import androidx.lifecycle.LiveData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.todoapplication.db.Todo

import com.example.todoapplication.repositroy.TodoRepo

import dagger.hilt.android.lifecycle.HiltViewModel


import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class  TodoViewmodel @Inject constructor(private val repository: TodoRepo) : ViewModel() {

    val allTodos: LiveData<List<Todo>> = repository.allTodos

    fun updateTodoStatus(todo: Todo) {
        viewModelScope.launch {
            repository.updateTodoStatus(todo)
        }
    }

    init {
        viewModelScope.launch {
            repository.fetchAndStoreTodos()
        }
    }


}