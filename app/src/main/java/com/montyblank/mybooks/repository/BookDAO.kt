package com.montyblank.mybooks.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.montyblank.mybooks.entity.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDAO {

    /*
    Itens que o Room consegue fornecer.
    * Entity
    * List<Entity>
    * **LiveData<Entity>
    * Flow<Entity>
    * Cursor
    * Int, log, Boolean
    * */

    @Query("SELECT * FROM books")
    fun getAllBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE favorite = 1")
    fun getFavoriteBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE id = :id")
    suspend fun getBookById(id: Int): BookEntity

    @Query("SELECT * FROM books WHERE title = :title")
    suspend fun getBookByTitle(title: String): BookEntity

    @Query("SELECT * FROM books WHERE author = :author")
    suspend fun getBookByAuthor(author: String): BookEntity

    @Update
    suspend fun update(book: BookEntity)

    @Delete
    suspend fun delete(book: BookEntity): Int

    @Insert
    suspend fun insertBook(book: List<BookEntity>)
}
