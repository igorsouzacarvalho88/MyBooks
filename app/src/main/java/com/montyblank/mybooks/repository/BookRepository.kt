package com.montyblank.mybooks.repository

import android.content.Context
import com.montyblank.mybooks.entity.BookEntity
import kotlinx.coroutines.flow.Flow

class BookRepository private constructor(context: Context) {

    private var dataBase = BookDatabase.getDatabase(context).bookDAO()

    //singleton
    companion object {
        private lateinit var instance: BookRepository

        fun getInstance(context: Context): BookRepository {

            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = BookRepository(context)
                }
            }
            return instance
        }
    }

    fun getAllBooks(): Flow<List<BookEntity>> {
        return dataBase.getAllBooks()
    }

    fun getFavoriteBooks(): Flow<List<BookEntity>> {
       return dataBase.getFavoriteBooks()
    }

    suspend fun getBookById(id: Int): BookEntity {
        return dataBase.getBookById(id)
    }

    suspend fun getBooksByTitle(title: String): BookEntity {
        return dataBase.getBookByTitle(title)
    }

    suspend fun getBooksByAuthor(author: String): BookEntity {
        return dataBase.getBookByAuthor(author)
    }

    suspend fun toggleFavorite(id: Int) {
        val book = getBookById(id)
        book.favorite = !book.favorite
        dataBase.update(book)
    }

    suspend fun deleteBook(id: Int): Boolean {
        return dataBase.delete(getBookById(id)) > 0
    }


}