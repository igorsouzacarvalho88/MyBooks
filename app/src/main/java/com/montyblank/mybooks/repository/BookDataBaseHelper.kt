package com.montyblank.mybooks.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.montyblank.mybooks.helper.DataBaseConstants

class BookDataBaseHelper(context: Context) :

    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        //Criação do banco de dados
        db.execSQL(CREATE_TABLE_BOOKS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    companion object{
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


}