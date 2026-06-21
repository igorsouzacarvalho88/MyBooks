package com.montyblank.mybooks.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.montyblank.mybooks.entity.BookEntity

@Dao
interface BookDAO {

    @Query("SELECT * FROM Book")
    fun getAllBooks(): List<BookEntity>

    @Query("SELECT * FROM Book WHERE favorite = 1")
    fun getFavoriteBooks(): List<BookEntity>

    @Query("SELECT * FROM Book WHERE id = :id")
    fun getBookById(id: Int): BookEntity?

    @Query("SELECT * FROM Book WHERE title = :title")
    fun getBookByTitle(title: String): BookEntity?

    @Query("SELECT * FROM Book WHERE author = :author")
    fun getBookByAuthor(author: String): BookEntity?

    @Query("UPDATE Book SET favorite = NOT favorite WHERE id = :id")
    fun toggleFavorite(id: Int)

    @Query("DELETE FROM Book WHERE id = :id")
    fun deleteBook(id: Int): Boolean

    @Insert
    fun insertBook(book: BookEntity): Long
}