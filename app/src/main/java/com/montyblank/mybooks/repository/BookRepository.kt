package com.montyblank.mybooks.repository

import android.content.ContentValues
import android.content.Context
import com.montyblank.mybooks.helper.DataBaseConstants
import com.montyblank.mybooks.entity.BookEntity

class BookRepository private constructor(context: Context) {

    private var database = BookDataBaseHelper(context)

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

    fun getAllBooks(): List<BookEntity> {
        val db = database.readableDatabase
        val books = mutableListOf<BookEntity>()

        //query
        val cursor = db.query(DataBaseConstants.BOOK.TABLE_NAME, null, null, null, null, null, null)

        //cursor
        if (cursor.moveToFirst()) {
            do {
                val id =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.BOOK.COLUMNS.ID))
                val title =
                    cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.BOOK.COLUMNS.TITLE))
                val author =
                    cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.BOOK.COLUMNS.AUTHOR))
                val genre =
                    cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.BOOK.COLUMNS.GENRE))
                val favorite: Boolean =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.BOOK.COLUMNS.FAVORITE)) == 1

                books.add(BookEntity(id, title, author, favorite, genre))

            } while (cursor.moveToNext())
        }

        //Criar a lista

        //fecho o bd / cursor
        cursor.close()
        db.close()

        //return
        return books
    }

    fun getFavoriteBooks(): List<BookEntity> {
        val db = database.readableDatabase
        val books = mutableListOf<BookEntity>()

        //query
        val cursor = db.query(
            DataBaseConstants.BOOK.TABLE_NAME,
            null,
            "${DataBaseConstants.BOOK.COLUMNS.FAVORITE} = ?",
            arrayOf("1"),
            null,
            null,
            null
        )

        //cursor
        if (cursor.moveToFirst()) {
            do {
                val id =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.BOOK.COLUMNS.ID))
                val title =
                    cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.BOOK.COLUMNS.TITLE))
                val author =
                    cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.BOOK.COLUMNS.AUTHOR))
                val genre =
                    cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.BOOK.COLUMNS.GENRE))
                val favorite: Boolean =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.BOOK.COLUMNS.FAVORITE)) == 1

                books.add(BookEntity(id, title, author, favorite, genre))

            } while (cursor.moveToNext())
        }

        //Criar a lista

        //fecho o bd / cursor
        cursor.close()
        db.close()

        //return
        return books
    }

    fun getBookById(id: Int): BookEntity? {
        val db = database.readableDatabase


        //query
        val cursor = db.query(
            DataBaseConstants.BOOK.TABLE_NAME,
            null,
            "${DataBaseConstants.BOOK.COLUMNS.ID} = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )

        //cursor
        var book: BookEntity? = null
        if (cursor.moveToFirst()) {
            do {
                val idValue =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.BOOK.COLUMNS.ID))
                val title =
                    cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.BOOK.COLUMNS.TITLE))
                val author =
                    cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.BOOK.COLUMNS.AUTHOR))
                val genre =
                    cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.BOOK.COLUMNS.GENRE))
                val favorite: Boolean =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.BOOK.COLUMNS.FAVORITE)) == 1

                book = (BookEntity(idValue, title, author, favorite, genre))

            } while (cursor.moveToNext())
        }

        //Criar a lista

        //fecho o bd / cursor
        cursor.close()
        db.close()

        //return
        return book
    }

    fun getBooksByTitle(title: String): BookEntity? {
        val db = database.readableDatabase


        //query
        val cursor = db.query(
            DataBaseConstants.BOOK.TABLE_NAME,
            null,
            "${DataBaseConstants.BOOK.COLUMNS.TITLE} = ?",
            arrayOf(title),
            null,
            null,
            null
        )

        //cursor
        var book: BookEntity? = null
        if (cursor.moveToFirst()) {
            do {
                val id =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.BOOK.COLUMNS.ID))
                val titleValue =
                    cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.BOOK.COLUMNS.TITLE))
                val author =
                    cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.BOOK.COLUMNS.AUTHOR))
                val genre =
                    cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.BOOK.COLUMNS.GENRE))
                val favorite: Boolean =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.BOOK.COLUMNS.FAVORITE)) == 1

                book = (BookEntity(id, titleValue, author, favorite, genre))

            } while (cursor.moveToNext())
        }

        //Criar a lista

        //fecho o bd / cursor
        cursor.close()
        db.close()

        //return
        return book

    }

    fun getBooksByAuthor(author: String): BookEntity? {
        val db = database.readableDatabase


        //query
        val cursor = db.query(
            DataBaseConstants.BOOK.TABLE_NAME,
            null,
            "${DataBaseConstants.BOOK.COLUMNS.AUTHOR} = ?",
            arrayOf(author),
            null,
            null,
            null
        )

        //cursor
        var book: BookEntity? = null
        if (cursor.moveToFirst()) {
            do {
                val id =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.BOOK.COLUMNS.ID))
                val title =
                    cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.BOOK.COLUMNS.TITLE))
                val authorValue =
                    cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.BOOK.COLUMNS.AUTHOR))
                val genre =
                    cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.BOOK.COLUMNS.GENRE))
                val favorite: Boolean =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.BOOK.COLUMNS.FAVORITE)) == 1

                book = (BookEntity(id, title, authorValue, favorite, genre))

            } while (cursor.moveToNext())
        }

        //Criar a lista

        //fecho o bd / cursor
        cursor.close()
        db.close()

        //return
        return book
    }

    fun toggleFavorite(id: Int) {
        val book = getBookById(id)
        val newFavoriteStatus = if (book?.favorite == true) 0 else 1

        val db = database.writableDatabase
        val values = ContentValues().apply {
            put(DataBaseConstants.BOOK.COLUMNS.FAVORITE, newFavoriteStatus)
        }
        db.update(
            DataBaseConstants.BOOK.TABLE_NAME,
            values,
            "${DataBaseConstants.BOOK.COLUMNS.ID} = ?",
            arrayOf(id.toString())
        )
        db.close()
    }

    fun deleteBook(id: Int): Boolean {
        val db = database.writableDatabase
        val rowsDeleted = db.delete(
            DataBaseConstants.BOOK.TABLE_NAME,
            "${DataBaseConstants.BOOK.COLUMNS.ID} = ?",
            arrayOf(id.toString())
        )
        db.close()
        return rowsDeleted > 0
    }


}