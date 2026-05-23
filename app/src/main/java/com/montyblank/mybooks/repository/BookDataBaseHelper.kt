package com.montyblank.mybooks.repository

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.montyblank.mybooks.entity.BookEntity
import com.montyblank.mybooks.helper.DataBaseConstants
import androidx.core.database.sqlite.transaction

class BookDataBaseHelper(context: Context) :

    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        //Criação do banco de dados
        db.execSQL(CREATE_TABLE_BOOKS)
        insertBooks(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        private const val DATABASE_NAME = "BooksDB"
        private const val DATABASE_VERSION = 1

        private const val CREATE_TABLE_BOOKS = """ 
            CREATE TABLE ${DataBaseConstants.BOOK.TABLE_NAME}(
            ${DataBaseConstants.BOOK.COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
           ${DataBaseConstants.BOOK.COLUMNS.TITLE} TEXT NOT NULL,
            ${DataBaseConstants.BOOK.COLUMNS.AUTHOR} TEXT NOT NULL,
            ${DataBaseConstants.BOOK.COLUMNS.GENRE} TEXT NOT NULL,
            ${DataBaseConstants.BOOK.COLUMNS.FAVORITE} INTEGER NOT NULL           
            );
            """
    }

    private fun insertBooks(db: SQLiteDatabase) {
        val books = getInitialBooks()
        db.transaction { //iniciar a transação

                for (book in books) {
                    val values = ContentValues().apply {
                        put(DataBaseConstants.BOOK.COLUMNS.ID, book.id)
                        put(DataBaseConstants.BOOK.COLUMNS.TITLE, book.title)
                        put(DataBaseConstants.BOOK.COLUMNS.AUTHOR, book.author)
                        put(DataBaseConstants.BOOK.COLUMNS.GENRE, book.genre)
                        put(DataBaseConstants.BOOK.COLUMNS.FAVORITE, if (book.favorite) 1 else 0)

                    }

                    insert(DataBaseConstants.BOOK.TABLE_NAME, null, values)
                }
        }
    }

    private fun getInitialBooks(): List<BookEntity> {
        return listOf(
            BookEntity(1, "To Kill a Mockingbird", "Harper Lee", true, "Ficção"),
            BookEntity(2, "Dom Casmurro", "Machado de Assis", false, "Romance"),
            BookEntity(3, "O Hobbit", "J.R.R. Tolkien", true, "Fantasia"),
            BookEntity(4, "Cem Anos de Solidão", "Gabriel García Márquez", false, "Romance"),
            BookEntity(5, "O Pequeno Príncipe", "Antoine de Saint-Exupéry", false, "Fantasia"),
            BookEntity(6, "Crime e Castigo", "Fiódor Dostoiévski", false, "Ficção policial"),
            BookEntity(7, "Frankenstein", "Mary Shelley", false, "Terror"),
            BookEntity(
                8,
                "Harry Potter e a Pedra Filosofal",
                "J.K. Rowling",
                false,
                "Fantasia"
            ),
            BookEntity(9, "Neuromancer", "William Gibson", false, "Cyberpunk"),
            BookEntity(10, "Senhor dos Anéis", "J.R.R. Tolkien", false, "Fantasia"),
            BookEntity(11, "Drácula", "Bram Stoker", false, "Terror"),
            BookEntity(12, "Orgulho e Preconceito", "Jane Austen", false, "Romance"),
            BookEntity(
                13,
                "Harry Potter e a Câmara Secreta",
                "J.K. Rowling",
                false,
                "Fantasia"
            ),
            BookEntity(14, "As Crônicas de Nárnia", "C.S. Lewis", false, "Fantasia"),
            BookEntity(15, "O Código Da Vinci", "Dan Brown", false, "Mistério"),
            BookEntity(16, "It: A Coisa", "Stephen King", false, "Terror"),
            BookEntity(17, "Moby Dick", "Herman Melville", true, "Aventura"),
            BookEntity(18, "O Nome do Vento", "Patrick Rothfuss", true, "Fantasia"),
            BookEntity(19, "O Conde de Monte Cristo", "Alexandre Dumas", true, "Aventura"),
            BookEntity(20, "Os Miseráveis", "Victor Hugo", false, "Romance")
        )
    }
}
