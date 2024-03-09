package com.example.todoapplication.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todoapplication.model.TodoResponseItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos")
    fun getAllTodos(): LiveData<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(todos: List<Todo>)

   /* @Query("UPDATE todos SET completed = :completed WHERE id = :todoId")
    suspend fun updateCompleted(todoId: Int, completed: Boolean)*/
    @Update
    suspend fun updateTodoStatus(todo: Todo)

}