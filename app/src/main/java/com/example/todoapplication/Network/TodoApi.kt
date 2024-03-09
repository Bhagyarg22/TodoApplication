package com.example.todoapplication.Network


import com.example.todoapplication.model.TodoResponse
import com.example.todoapplication.model.TodoResponseItem
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface TodoApi {
    @GET("todos")
    suspend fun getTodos(): List<TodoResponseItem>
}