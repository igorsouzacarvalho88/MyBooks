package com.montyblank.mybooks.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.montyblank.mybooks.entity.BookEntity

@Dao
interface BookDAO {

    @Query("SELECT * FROM books")
    fun getAllBooks(): List<BookEntity>

    @Query("SELECT * FROM books WHERE favorite = 1")
    fun getFavoriteBooks(): List<BookEntity>

    @Query("SELECT * FROM books WHERE id = :id")
    fun getBookById(id: Int): BookEntity

    @Query("SELECT * FROM books WHERE title = :title")
    fun getBookByTitle(title: String): BookEntity

    @Query("SELECT * FROM books WHERE author = :author")
    fun getBookByAuthor(author: String): BookEntity

    @Update
    fun update(book: BookEntity)

    @Delete
    fun delete(book: BookEntity): Int

    @Insert
    fun insertBook(book: List<BookEntity>)
}
